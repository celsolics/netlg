package com.csli.netlg.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.csli.netlg.domain.Categoria;
import com.csli.netlg.dto.CategoriaDto;
import com.csli.netlg.services.CategoriaService;
import com.csli.netlg.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value="/categorias") //endpoint
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	private URI uri;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //verbos http
	public ResponseEntity<Categoria> find(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/** @RequestBody essa anotação converte o json em objeto java automaticamente */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody CategoriaDto objDto){
		Categoria obj = service.fromDTO(objDto);
		obj  = service.insert(obj);
		uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody CategoriaDto objDto, @PathVariable Integer id){
		Categoria obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET) //verbos http
	public ResponseEntity<CategoriaDto> findAll() throws ObjectNotFoundException {
		List<Categoria> lista = service.findAll();
		/** Converte uma lista de categoria em uma lista DTO, seguido do construtor CategoriaDto que recebe uma Categoria como parametro*/
		List<CategoriaDto> listaDto = lista.stream().map(obj ->  new CategoriaDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET) //verbos http
	public ResponseEntity<Page<CategoriaDto>> findPage(
			@RequestParam(value="page", defaultValue = 0) Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = 24) Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		Page<Categoria> lista = service.findPage(page, linesPerPage, orderBy, direction);
		/** Converte uma lista de categoria em uma lista DTO, seguido do construtor CategoriaDto que recebe uma Categoria como parametro*/
		Page<CategoriaDto> listaDto = lista.map(obj ->  new CategoriaDto(obj));
		return ResponseEntity.ok().body(listaDto);
	}
	
	

}
