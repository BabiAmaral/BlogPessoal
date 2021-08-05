package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //anotação de que se trata de um repositório
public interface PostagemRepository extends JpaRepository<Postagem, Long>
{
	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);/*método trás tudo o que ta 
	no titulo sem levar em consideração o maiusculo ou minusculo(ignoreCase)*/
}
