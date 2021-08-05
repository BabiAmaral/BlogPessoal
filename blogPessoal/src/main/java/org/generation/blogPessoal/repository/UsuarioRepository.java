package org.generation.blogPessoal.repository;

import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
	//metodo para pegar um usuario e um username
	public Optional <Usuario> findByUsuario(String usuario); // Optional- pode ou não vir, ou seja, os valores podem vir nulos
}
