package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService//cripitografia
{
	@Autowired
	private UsuarioRepository repository;
	
	//metodo/ regra de negocio do usuario para cadastrar
	public Optional<Object> cadastrarUsuario(Usuario novoUsuario) {
		return repository.findByUsuario(novoUsuario.getUsuario()).map(usuarioExistente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(senhaCriptografada);
			return Optional.ofNullable(repository.save(novoUsuario));
		});
	}
	/*
	//outra forma de fazer regra de negócio do logar// metodo do prof Marcelo
	public Optional<UserLogin> Logar(Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();//instanciando o encoder
		
		Optional<Usuario> usuario= repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) { //tiver algo dentro dele
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {  //se na comparação foi igual, vai fazer a regra de negócio e devolver a senha encriptada
			
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();// juntar duas infos, o usuario e sua senha
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);		
				
				user.get().setToken(authHeader);				
				user.get().setNome(usuario.get().getNome());
				user.get().setSenha(usuario.get().getSenha());//não foi adicionado na video aula

				return user;
				}
		}
		return null; }*/
	
	//regra de negócio do logar
	public Optional<?> logar (UserLogin usuarioParaLogin) {
		return repository.findByUsuario(usuarioParaLogin.getUsuario()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(usuarioParaLogin.getSenha(), usuarioExistente.getSenha())) {
				String estruturaBasic = usuarioParaLogin.getUsuario() + ":" + usuarioParaLogin.getSenha(); 
				byte[] autorizacaoBase64 = Base64.encodeBase64(estruturaBasic.getBytes(Charset.forName("US-ASCII"))); 
				String autorizacaoHeader = "Basic " + new String(autorizacaoBase64);

				usuarioParaLogin.setToken(autorizacaoHeader);
				usuarioParaLogin.setId(usuarioExistente.getId());
				usuarioParaLogin.setNome(usuarioExistente.getNome());
				usuarioParaLogin.setSenha(usuarioExistente.getSenha());
				return Optional.ofNullable(usuarioParaLogin);
			} else {
				return Optional.empty();
			}
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
	//regra de negócio para alterar o cadastro
	public Optional<?> alterarUsuario(UserLogin usuarioParaAlterar) {
		return repository.findById(usuarioParaAlterar.getId()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(usuarioParaAlterar.getSenha());

			usuarioExistente.setNome(usuarioParaAlterar.getNome());
			usuarioExistente.setUsuario(usuarioParaAlterar.getUsuario());
			usuarioExistente.setSenha(senhaCriptografada);
			
			return Optional.ofNullable(repository.save(usuarioExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
}
