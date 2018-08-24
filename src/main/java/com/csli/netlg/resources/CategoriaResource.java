package com.csli.netlg.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csli.netlg.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias") //endpoint
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}" method=RequestMethod.GET) //verbos http
	public ResponseEntity<?> find(#PathVariable Integer id) {
		
		Categoria obj = service.findOne(id);
		return ResponseEntity.Ok().body(obj);		
	}

}
