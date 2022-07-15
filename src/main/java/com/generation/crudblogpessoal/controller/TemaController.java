package com.generation.crudblogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.generation.crudblogpessoal.model.Tema;
import com.generation.crudblogpessoal.repository.TemaRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders= "*")
@RequestMapping("/tema")
public class TemaController {
	
	@Autowired
	private TemaRepository temarepository;
	
	@GetMapping
	public ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(temarepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable Long id){
		return temarepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> getByName(@PathVariable String descricao){
		return ResponseEntity.ok(temarepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	@PostMapping
	public ResponseEntity<Tema> postTema(@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(temarepository.save(tema));
	}
	@PutMapping
	
	public ResponseEntity<Tema> putTema(@Valid @RequestBody Tema tema) {
		
	return temarepository.findById(tema.getId())
			.map(resposta -> {
				return ResponseEntity.ok().body(temarepository.save(tema));
			})
			.orElse(ResponseEntity.notFound().build());

}
	@DeleteMapping("/{id}")
	
    public ResponseEntity<?> deleteTema(@PathVariable Long id) {
		
		return temarepository.findById(id)
				.map(resposta -> {
					temarepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
