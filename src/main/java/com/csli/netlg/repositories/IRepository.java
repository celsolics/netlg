package com.csli.netlg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepository extends JpaRepository<Object, Integer>{
  /**
   * Através da anotação @Repositoy e extendendo a classe para JpaRepository
   * é possível fazer todas as operações do CRUD
   */
}