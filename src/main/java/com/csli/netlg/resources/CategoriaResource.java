package com.csli.netlg.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categorias") //endpoint
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET) //verbos http
	public String listar() {
		return "Celso Gomes";
	}

}
