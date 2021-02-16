package com.carlosbotelho.curseprojec.resources;

import com.carlosbotelho.curseprojec.domain.Category;
import com.carlosbotelho.curseprojec.domain.Order;
import com.carlosbotelho.curseprojec.services.CategoryService;
import com.carlosbotelho.curseprojec.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id){
        Order obj = orderService.findBy(id);

        return ResponseEntity.ok().body(obj);
    }

}
