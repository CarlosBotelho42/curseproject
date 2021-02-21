package com.carlosbotelho.curseprojec.domain.payment;

import com.carlosbotelho.curseprojec.domain.Order;
import com.carlosbotelho.curseprojec.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CardPayment_tb")
@JsonTypeName("cardPayment")
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
