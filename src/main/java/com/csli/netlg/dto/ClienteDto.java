package com.csli.netlg.dto;

import java.io.Serializable;

import com.csli.netlg.domain.Cliente;
import javax.valitadion.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ClienteDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Campo Obrigatório")
	@Length(min=5, max=80, message="Tamanho entre 5 e 80")
	private String nome;
	
	@NotEmpty
	@Email(message="Email inválido")
	private String email;
	
	public ClienteDto() {
		
	}
	
	/**Desse modo ele instancia o obj Dto pela entidade categoria */
	public ClienteDto(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail(); 
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
