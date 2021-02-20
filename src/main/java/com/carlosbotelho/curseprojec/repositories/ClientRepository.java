package com.carlosbotelho.curseprojec.repositories;

import com.carlosbotelho.curseprojec.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// Essa camada da aplicação vai ser responsavel por acessar e fazer a consulta nas tabelas do banco
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    //@ Ela faz com que essa operação não seja envolvida com uma transação no banco de dados
    //Fazendo ela ficar mais rapida e diminuindo o Locking no gerenciamento de transações no banco
    @Transactional(readOnly = true)
    Client findByEmail(String email);
    //O Spring Data automaticamente vai detectar a busca por email e vai implemtar o metodo

}
