package com.carlosbotelho.curseprojec.domain;

import com.carlosbotelho.curseprojec.domain.item.OrderItem;
import com.carlosbotelho.curseprojec.domain.payment.Payment;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "Order_tb")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instant;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    //@JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    //@JsonManagedReference
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

    //Percorrer a lista de items do pedido, para cada OrderItem na minha lista de items = somar com SubTotal!!!
    public double getTotalValue(){
//        double sum = 0.0;
//        for (OrderItem oi : items){
//            sum =sum + oi.getSubtotal();
//        }
        return items.stream().mapToDouble(x -> x.getSubtotal()).sum();
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

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
        final StringBuilder sb = new StringBuilder();

        sb.append("Número do pedido: ");
        sb.append(getId());
        sb.append(", Instante: ");
        sb.append(sdf.format(getInstant()));
        sb.append(", Cliente: ");
        sb.append(getClient().getName());
        sb.append(", Situação de pagamento: ");
        sb.append(getPayment().getPaymentStatus().getDescription());
        sb.append("\nDetalhes:\n");
        for (OrderItem orderItem : getItems()){
            sb.append(orderItem.toString());
        }
        sb.append("Valor total: ");
        sb.append(nf.format(getTotalValue()));



        return sb.toString();
    }
}
