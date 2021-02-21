package com.carlosbotelho.curseprojec.resources;

import com.carlosbotelho.curseprojec.domain.Category;
import com.carlosbotelho.curseprojec.domain.Order;
import com.carlosbotelho.curseprojec.dto.CategoryDTO;
import com.carlosbotelho.curseprojec.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping (value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id){
        Order obj = service.findBy(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Order obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
