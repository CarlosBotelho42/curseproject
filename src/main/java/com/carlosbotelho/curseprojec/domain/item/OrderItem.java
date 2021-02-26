package com.carlosbotelho.curseprojec.domain.item;

import com.carlosbotelho.curseprojec.domain.Order;
import com.carlosbotelho.curseprojec.domain.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name = "OrderItem_tb")
public class OrderItem implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Double discount;
    private Integer amount;
    private Double price;

    public OrderItem(){

    }

    public OrderItem(Product product, Order order, Double discount, Integer amount, Double price) {
        id.setProduct(product);
        id.setOrder(order);
        this.discount = discount;
        this.amount = amount;
        this.price = price;
    }

    public double getSubtotal(){
        return (price - discount) * amount;
    }

    public Product getProduct(){
        return id.getProduct();
    }

    public void setProduct(Product product){
         id.setProduct(product);
    }

    @JsonIgnore
    public Order getOrder(){
        return id.getOrder();
    }

    public void setOrder(Order order){
        id.setOrder(order);
    }

    public OrderItemPK getId() {
        return id;
    }

    public void setId(OrderItemPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id.equals(orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        final StringBuilder sb = new StringBuilder();

        sb.append(getProduct().getName());
        sb.append(", Quantidade: ");
        sb.append(getAmount());
        sb.append(", Preço unitário: ");
        sb.append(nf.format(getPrice()));
        sb.append(", Subtotal: ");
        sb.append(nf.format(getSubtotal()));
        sb.append("\n");

        return sb.toString();
    }
}
