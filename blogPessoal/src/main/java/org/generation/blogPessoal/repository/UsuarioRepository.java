package org.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
	//metodo para pegar um usuario e um username
	public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
	public Optional<Usuario> findByUsuarioAndSenha(String usuario, String senha);
	public Optional <Usuario> findByUsuario(String usuario); // Optional- pode ou não vir, ou seja, os valores podem vir nulos
	//o intuito aqui é buscar um único usuario, por isso tem que ser algo único: um nome de usuario, ou email, ou cpf
	
	
}
