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
	public Usuario CadastrarUsuario(Usuario usuario) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String senhaCriptografada = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);
			
			return repository.save(usuario);
		
	}

	public Optional<UserLogin> Logar(Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();//instanciando o encoder
		
		Optional<Usuario> usuario= repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) { //tiver algo dentro dele
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {  //se na comparação foi igual, vai fazer a regra de negócio e devolver a senha encriptada
			
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();// juntar duas infos, o usuario e sua senha
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);		
				
				user.get().setToken(authHeader);
				user.get().setId(usuario.get().getId());
				user.get().setNome(usuario.get().getNome());
				user.get().setFoto(usuario.get().getFoto());
				user.get().setTipo(usuario.get().getTipo());

				return user;
				}
		}
		return null; 
		
	}
	
	
}
