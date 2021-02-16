package com.carlosbotelho.curseprojec.domain.payment;

import com.carlosbotelho.curseprojec.domain.Order;
import com.carlosbotelho.curseprojec.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tb_TicketPayment")
public class TicketPayment extends Payment{

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date payDay;

    public TicketPayment(){

    }

    public TicketPayment(Integer id, PaymentStatus paymentStatus, Order order, Date dueDate, Date payDay) {
        super(id, paymentStatus, order);
        this.dueDate = dueDate;
        this.payDay = payDay;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPayDay() {
        return payDay;
    }

    public void setPayDay(Date payDay) {
        this.payDay = payDay;
    }
}
