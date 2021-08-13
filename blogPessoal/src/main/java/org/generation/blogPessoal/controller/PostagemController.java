package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.generation.blogPessoal.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
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
//import org.springframework.stereotype.Component;


//@Component
@RestController
@RequestMapping("/postagens")
@CrossOrigin("*") //a classe aceita requisições de qualquer origem
public class PostagemController
{
	@Autowired //garante que todos serviços da interface repository seja acessado a partir do  controller
	private PostagemRepository repository;
	@Autowired
	private PostagemService service;
	
	@PostMapping("/salvar")
	public ResponseEntity<Object> cadastrarPostagem(@Valid @RequestBody Postagem novaPostagem) {
		Optional<?> objetoCadastrado = service.cadastrarPostagem(novaPostagem);

		if (objetoCadastrado.isPresent()) {
			return ResponseEntity.status(201).body(objetoCadastrado.get());
		} else {
			return ResponseEntity.status(400).build();
		}

	}

	@GetMapping ("/todas")
	public ResponseEntity<Object> buscarTodas() {
		List<Postagem> listaPostagem = repository.findAll();

		if (listaPostagem.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaPostagem);
		}

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> buscarPorId(@PathVariable long id){ //podendo ter  (value= "id") depois de pathvarieble(que recepciona a informação)
		                                                                //buscarPorId é o nome do metodo que eu crio
		return repository.findById(id) //metodo
		     .map(resp -> ResponseEntity.ok(resp)) //.map tem dentro do objeto optional/retorna o ok
			 .orElse(ResponseEntity.notFound().build());//orElse, outro metodo dentro de optional/ retorna caso não exista ou dê erro
	}
	
	/*Outra forma de fazer o método findById... Nesta quando colocado um num de id que não tem, dá erro 500
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> buscarPorId(@PathVariable (value="id") long id){
		return ResponseEntity.status(200).body(repository.findById(id).get());//FindById
	}
	*/
	
	@GetMapping("/titulo/{titulo}") //precisamos colocar algo que não seja atributo na frente para não confundir o caminho.
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
/*
	@PostMapping //insersão  //no postman não passa o id
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){ //como é algo grande, notação pata pegar o corpo
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
*/
	@PutMapping("/alterar")
	public ResponseEntity<Object> alterar(@Valid @RequestBody Postagem postagemParaAlterar) {
		Optional<Postagem> objetoAlterado = service.alterarPostagem(postagemParaAlterar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}
	/*
	@PutMapping  //atualização //no postman passa o id e faz a atualização
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){ //como é algo grande, notação pata pegar o corpo
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem)); //devolvendo o objeto salvo	
	}
	*/
	@DeleteMapping ("/deletar/{id}")
	public ResponseEntity<Object> deletarPorId(@PathVariable(value = "id") long id) {
		Optional<Postagem> objetoExistente = repository.findById(id);
		if (objetoExistente.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}
		
	}
	
}        




