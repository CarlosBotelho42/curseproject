package com.carlosbotelho.curseprojec.services;

import com.carlosbotelho.curseprojec.domain.*;
import com.carlosbotelho.curseprojec.domain.Client;
import com.carlosbotelho.curseprojec.domain.enums.ClientRole;
import com.carlosbotelho.curseprojec.dto.ClientDTO;
import com.carlosbotelho.curseprojec.dto.ClientNewDTO;
import com.carlosbotelho.curseprojec.repositories.AddressRepository;
import com.carlosbotelho.curseprojec.repositories.ClientRepository;
import com.carlosbotelho.curseprojec.services.exceptions.DataIntegrityViolation;
import com.carlosbotelho.curseprojec.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    @Autowired
    private AddressRepository addressRepository;

    public Client findBy(Integer id){
        Optional<Client> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotfoundException(
                "Objeto não encontrado! Id: "
                        + id
                        + ", Tipo: "
                        + Client.class.getName()
        )) ;
    }

    @Transactional
    public Client insert(Client obj){
        obj.setId(null);
        obj = repo.save(obj);
        addressRepository.saveAll(obj.getAddresses());
        return obj;
    }

    public Client update(Client obj){
       Client newObj = findBy(obj.getId());
       updateData(newObj, obj);
        return  repo.save(newObj);
    }

    private void updateData(Client newObj, Client obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public void delete(Integer id){
        findBy(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolation("Não é possível excluir, pois há entidades relacionadas!");
        }
    }

    public List<Client> findAll(){
        return repo.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Client fromDto(ClientDTO objDto){
        return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
    }

    public Client fromDto(ClientNewDTO objDto){
        Client client = new Client(null, objDto.getName(),objDto.getEmail(),objDto.getCpfOrCnpj(), ClientRole.toEnum(objDto.getClientRole()));
        City city = new City(objDto.getCityId(),null,null);
        Address address = new Address(null, objDto.getLogrdouro(),objDto.getNumber(),objDto.getComplement(),objDto.getDistrict(),objDto.getCep(), client,city);

        client.getAddresses().add(address);
        client.getPhones().add(objDto.getPhone1());

        if (objDto.getPhone2() != null){
            client.getPhones().add(objDto.getPhone2());
        }
        if (objDto.getPhone3() != null){
            client.getPhones().add(objDto.getPhone3());
        }

        return client;
    }

}
