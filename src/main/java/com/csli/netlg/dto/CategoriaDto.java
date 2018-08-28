package com.csli.netlg.dto;

import java.io.Serializable;

import com.csli.netlg.domain.Categoria;

public class CategoriaDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	@NotEmpty(message="Campo Obrigatório")
	@Length(min=5, max=80, message="Tamanho entre 5 e 80")
	private String nome;
	
	public CategoriaDto() {
		
	}
	
	/**Desse modo ele instancia o obj Dto pela entidade categoria */
	public CategoriaDto(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
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
	
	

}
