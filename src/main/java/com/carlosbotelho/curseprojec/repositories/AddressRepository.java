package com.carlosbotelho.curseprojec.repositories;

import com.carlosbotelho.curseprojec.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Essa camada da aplicação vai ser responsavel por acessar e fazer a consulta nas tabelas do banco
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
