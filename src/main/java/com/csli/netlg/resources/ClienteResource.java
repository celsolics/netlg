package com.csli.netlg.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.csli.netlg.domain.Cliente;
import com.csli.netlg.dto.ClienteDto;
import com.csli.netlg.dto.ClienteNewDto;
import com.csli.netlg.services.ClienteService;
import com.csli.netlg.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value="/clientes") //endpoint
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //verbos http
	public ResponseEntity<Cliente> find(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/** @RequestBody essa anotação converte o json em objeto java automaticamente */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody ClienteNewDto objDto){
		Cliente obj = service.fromDTO(objDto);
		obj  = service.insert(obj);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ClienteDto objDto, @PathVariable Integer id){
		Cliente obj = service.fromDtoCliente(objDto);
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
	public ResponseEntity<List<ClienteDto>> findAll() throws ObjectNotFoundException {
		List<Cliente> lista = service.findAll();
		/** Converte uma lista de categoria em uma lista DTO, seguido do construtor ClienteDto que recebe uma Cliente como parametro*/
		List<ClienteDto> listaDto = lista.stream().map(obj ->  new ClienteDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET) //verbos http
	public ResponseEntity<Page<ClienteDto>> findPage(
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		Page<Cliente> lista = service.findPage(page, linesPerPage, orderBy, direction);
		/** Converte uma lista de categoria em uma lista DTO, seguido do construtor ClienteDto que recebe uma Cliente como parametro*/
		Page<ClienteDto> listaDto = lista.map(obj ->  new ClienteDto(obj));
		return ResponseEntity.ok().body(listaDto);
	}

}
