package com.csli.netlg.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csli.netlg.domain.Categoria;
import com.csli.netlg.repositories.CategoriaRepository;
import com.csli.netlg.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	/**
	 * A anotação autowired faz a injeção de dependência sem precisar instanciar a classe
	 */
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
				", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

}
