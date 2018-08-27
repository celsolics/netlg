package com.csli.netlg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csli.netlg.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
  /**
   * Através da anotação @Repositoy e extendendo a classe para JpaRepository
   * é possível fazer todas as operações do CRUD
   */
}
