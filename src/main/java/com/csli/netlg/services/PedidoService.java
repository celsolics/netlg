package com.csli.netlg.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csli.netlg.domain.Pedido;
import com.csli.netlg.repositories.PedidoRepository;
import com.csli.netlg.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	/**
	 * A anotação autowired faz a injeção de dependência sem precisar instanciar a classe
	 */
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
				", Tipo: " + Pedido.class.getName()));
	}

}
