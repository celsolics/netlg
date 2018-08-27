package com.csli.netlg.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.csli.netlg.domain.enums.EstadoPagamento;

@Entity
@Inheritance(strategy=InheritanceType.JOINED) /*Essa abordagem é para criar uma tabela para tipo de pagamento*/

/**
 * Essa classe é abstract para n ser instanciada, ela vai ser acessada pelas suas subclasses: PagtoComCartao e PagtoComBoleto
 * @author celso
 *
 */
public abstract class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private Integer estadoPagto;
	
	/***
	 * Nesse trecho o id do pagamento n é gerado automaticamente e sim pelo id do pedido
	 */
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId
	private Pedido pedido;
	
	public Pagamento() {}

	public Pagamento(Integer id, EstadoPagamento estadoPagto, Pedido pedido) {
		super();
		this.id = id;
		this.estadoPagto = estadoPagto.getCod();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstadoPagto() {
		return EstadoPagamento.toEnum(estadoPagto);
	}

	public void setEstadoPagto(EstadoPagamento estadoPagto) {
		this.estadoPagto = estadoPagto.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}