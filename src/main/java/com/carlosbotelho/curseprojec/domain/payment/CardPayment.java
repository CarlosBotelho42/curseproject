package com.carlosbotelho.curseprojec.domain.payment;

import com.carlosbotelho.curseprojec.domain.Order;
import com.carlosbotelho.curseprojec.domain.enums.PaymentStatus;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_CardPayment")
public class CardPayment extends Payment{

    private Integer numberInstallments;

    public CardPayment(){

    }

    public CardPayment(Integer id, PaymentStatus paymentStatus, Order order, Integer numberInstallments) {
        super(id, paymentStatus, order);
        this.numberInstallments = numberInstallments;
    }

    public Integer getNumberInstallments() {
        return numberInstallments;
    }

    public void setNumberInstallments(Integer numberInstallments) {
        this.numberInstallments = numberInstallments;
    }
}
