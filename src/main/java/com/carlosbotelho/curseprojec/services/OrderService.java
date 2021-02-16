package com.carlosbotelho.curseprojec.services;

import com.carlosbotelho.curseprojec.domain.Category;
import com.carlosbotelho.curseprojec.domain.Order;
import com.carlosbotelho.curseprojec.repositories.OrderRepository;
import com.carlosbotelho.curseprojec.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Serviço capaz de atender o controlador Rest relacionado a operações com categorias
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order findBy(Integer id){
        Optional<Order> obj = orderRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotfoundException(
                "Objeto não encontrado! Id: "
                + id
                + ", Tipo: "
                + Order.class.getName()
        ));
    }

}
