package com.carlosbotelho.curseprojec.services.util;

import com.carlosbotelho.curseprojec.domain.payment.TicketPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TicketService {

    public void fillTicketPayment(TicketPayment payment, Date instantOrder){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instantOrder);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        payment.setDueDate(calendar.getTime());

    }

}
