package com.carlosbotelho.curseprojec.domain.item;

import com.carlosbotelho.curseprojec.domain.Order;
import com.carlosbotelho.curseprojec.domain.Product;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

//Quando fazemos uma entidade tendo como atributo outra classe, colocamos essa notação para falar que ela vai ser rum subtipo
@Embeddable
public class OrderItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return product.equals(that.product) && order.equals(that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, order);
    }
}
