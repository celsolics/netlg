package com.csli.netlg.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csli.netlg.domain.Pedido;
import com.csli.netlg.services.PedidoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/pedidos") //endpoint
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //verbos http
	public ResponseEntity<Pedido> find(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

}
