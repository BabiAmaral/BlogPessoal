package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.generation.blogPessoal.service.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin ( origins  =  " * " , allowedHeaders  =  " * " ) //independente da origem, liberado todos os headers
@RequestMapping("/tema")
public class TemaController
{
	@Autowired
	private TemaRepository repository;
	@Autowired
	private TemaService service;

	@GetMapping("/todos")
	public ResponseEntity<Object> getAll() {
		List<Tema> listaTema = repository.findAll();

		if (listaTema.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaTema);
		}

	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable(value = "id") long id) {
		Optional<Tema> objetoTema = repository.findById(id);
		if (objetoTema.isPresent()) {
			return ResponseEntity.status(200).body(objetoTema.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}
	

	@GetMapping ("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> getByName(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao)); //método criado para buscar por uma parte do nome, semalhante ao like do mysql
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Object> post (@Valid @RequestBody Tema novoTema){  //usa-se o requestBody, pois Tema é uma entidade
	
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(novoTema));
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<Object> alterar(@Valid @RequestBody Tema temaParaAlterar) {
		Optional<Tema> objetoAlterado = service.alterarTema(temaParaAlterar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Object> deletarPorId(@PathVariable(value = "id") long id) {
		Optional<Tema> objetoExistente = repository.findById(id);
		if (objetoExistente.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}

	}

}
