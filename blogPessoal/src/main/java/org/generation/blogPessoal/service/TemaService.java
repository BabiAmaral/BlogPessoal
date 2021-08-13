package org.generation.blogPessoal.service;

import java.util.Optional;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemaService
{
	@Autowired
	private TemaRepository repositoryT;
	
	public Optional<Tema> alterarTema(Tema temaParaAlterar) {
		return repositoryT.findById(temaParaAlterar.getId()).map(temaExistente -> {
			temaExistente.setDescricao(temaParaAlterar.getDescricao());
			return Optional.ofNullable(repositoryT.save(temaExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
 
}
