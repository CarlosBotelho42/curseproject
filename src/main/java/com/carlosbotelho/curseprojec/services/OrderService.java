package com.carlosbotelho.curseprojec.services;

import com.carlosbotelho.curseprojec.domain.Category;
import com.carlosbotelho.curseprojec.domain.Order;
import com.carlosbotelho.curseprojec.domain.enums.PaymentStatus;
import com.carlosbotelho.curseprojec.domain.item.OrderItem;
import com.carlosbotelho.curseprojec.domain.payment.Payment;
import com.carlosbotelho.curseprojec.domain.payment.TicketPayment;
import com.carlosbotelho.curseprojec.repositories.OrderItemRepository;
import com.carlosbotelho.curseprojec.repositories.OrderRepository;
import com.carlosbotelho.curseprojec.repositories.PaymentRepository;
import com.carlosbotelho.curseprojec.repositories.ProductRepository;
import com.carlosbotelho.curseprojec.services.exceptions.ObjectNotfoundException;
import com.carlosbotelho.curseprojec.services.util.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

//Serviço capaz de atender o controlador Rest relacionado a operações com categorias
@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ProductService productService;

    public Order findBy(Integer id){
        Optional<Order> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotfoundException(
                "Objeto não encontrado! Id: "
                + id
                + ", Tipo: "
                + Order.class.getName()
        ));
    }

    @Transactional
    public Order insert(Order obj){
        obj.setId(null);
        obj.setInstant(new Date());
        obj.getPayment().setPaymentStatus(PaymentStatus.PENDENTE);
        obj.getPayment().setOrder(obj);

        if(obj.getPayment() instanceof TicketPayment){
            TicketPayment payment = (TicketPayment) obj.getPayment();
        }

        obj = repo.save(obj);
        paymentRepository.save(obj.getPayment());

        for(OrderItem oi : obj.getItems()){
            oi.setDiscount(0.0);
            oi.setPrice(productService.findBy(oi.getProduct().getId()).getPrice());
            oi.setOrder(obj);
        }
        orderItemRepository.saveAll(obj.getItems());
        return obj;
    }

}
