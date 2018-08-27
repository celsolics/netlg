package com.csli.netlg.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csli.netlg.domain.Categoria;
import com.csli.netlg.services.CategoriaService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/categorias") //endpoint
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //verbos http
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/** @RequestBody essa anotação converte o json em objeto java automaticamente */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj  = service.insert(obj);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
