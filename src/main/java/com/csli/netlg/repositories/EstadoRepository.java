package com.csli.netlg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csli.netlg.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{
  /**
   * Através da anotação @Repositoy e extendendo a classe para JpaRepository
   * é possível fazer todas as operações do CRUD
   */
}
