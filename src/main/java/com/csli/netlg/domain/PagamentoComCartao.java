package com.csli.netlg.domain;

import javax.persistence.Entity;

import com.csli.netlg.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	private Integer numerodeParcelas;
	
	public PagamentoComCartao() {}

	public PagamentoComCartao(Integer id, EstadoPagamento estadoPagto, Pedido pedido, Integer numerodeParcelas) {
		super(id, estadoPagto, pedido);
		this.numerodeParcelas = numerodeParcelas;
	}

	public Integer getNumerodeParcelas() {
		return numerodeParcelas;
	}

	public void setNumerodeParcelas(Integer numerodeParcelas) {
		this.numerodeParcelas = numerodeParcelas;
	}
	
}
