package com.carlosbotelho.curseprojec.services;

import com.carlosbotelho.curseprojec.domain.Category;
import com.carlosbotelho.curseprojec.domain.Client;
import com.carlosbotelho.curseprojec.repositories.ClientRepository;
import com.carlosbotelho.curseprojec.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client findBy(Integer id){
        Optional<Client> obj = clientRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotfoundException(
                "Objeto não encontrado! Id: "
                        + id
                        + ", Tipo: "
                        + Client.class.getName()
        )) ;
    }

}
