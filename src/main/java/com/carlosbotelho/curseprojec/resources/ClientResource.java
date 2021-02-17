package com.carlosbotelho.curseprojec.resources;

import com.carlosbotelho.curseprojec.domain.Client;
import com.carlosbotelho.curseprojec.domain.Client;
import com.carlosbotelho.curseprojec.domain.Client;
import com.carlosbotelho.curseprojec.dto.ClientDTO;
import com.carlosbotelho.curseprojec.dto.ClientDTO;
import com.carlosbotelho.curseprojec.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable Integer id){
        Client obj = service.findBy(id);

        return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDto, @PathVariable Integer id){
        Client obj = service.fromDto(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<Client> list = service.findAll();
        List<ClientDTO> listDto = list.stream()
                .map(obj -> new ClientDTO(obj))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClientDTO>> findPage(

            @RequestParam(value ="page", defaultValue ="0") Integer page,
            @RequestParam(value ="linesPerPage", defaultValue ="24") Integer linesPerPage,
            @RequestParam(value ="orderBy", defaultValue ="name") String orderBy,
            @RequestParam(value ="direction", defaultValue ="ASC") String direction){

        Page<Client> list = service.findPage(page, linesPerPage, orderBy, direction );
        Page<ClientDTO> listDto = list.map(obj -> new ClientDTO(obj));

        return ResponseEntity.ok().body(listDto);
    }
}
