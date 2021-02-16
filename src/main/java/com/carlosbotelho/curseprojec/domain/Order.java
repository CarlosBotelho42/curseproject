package com.carlosbotelho.curseprojec.domain;

import com.carlosbotelho.curseprojec.domain.item.OrderItem;
import com.carlosbotelho.curseprojec.domain.payment.Payment;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_Order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date instant;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();


    public Order(){

    }

    public Order(Integer id, Date instant, Address deliveryAddress, Client client) {
        this.id = id;
        this.instant = instant;
        this.deliveryAddress = deliveryAddress;
        this.client = client;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}