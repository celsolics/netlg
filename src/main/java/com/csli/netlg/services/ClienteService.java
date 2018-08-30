package com.csli.netlg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csli.netlg.domain.Cidade;
import com.csli.netlg.domain.Cliente;
import com.csli.netlg.domain.Endereco;
import com.csli.netlg.domain.enums.TipoCliente;
import com.csli.netlg.dto.ClienteNewDto;
import com.csli.netlg.repositories.CidadeRepository;
import com.csli.netlg.repositories.ClienteRepository;
import com.csli.netlg.repositories.EnderecoRepository;
import com.csli.netlg.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	/**
	 * A anotação autowired faz a injeção de dependência sem precisar instanciar a classe
	 */
	@Autowired
	private ClienteRepository rCliente;
	
	@Autowired
	private EnderecoRepository rEndereco;
	
	@Autowired
	private CidadeRepository rCidade;
	
	public Cliente find(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = rCliente.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
				", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = rCliente.save(obj);
		rEndereco.saveAll(obj.getEnderecos());
		return obj;		
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		find(obj.getId());
		return rCliente.save(obj);
	}
	
	public void delete(Integer id) {
		try {
			find(id);
			rCliente.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Cliente não pode ser excluida, contem pedido.");
		}
	}
	
	public List<Cliente> findAll(){
		return rCliente.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return rCliente.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteNewDto objDto) {
		Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfouCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cidade = rCidade.findOne(objDto.getCidadeId()); 
		//Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2() != null) {
			cliente.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3() != null) {
			cliente.getTelefones().add(objDto.getTelefone3());
		}
		
		return cliente;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
