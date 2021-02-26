package com.carlosbotelho.curseprojec.services.Email;

import com.carlosbotelho.curseprojec.domain.Order;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Order obj);

    void sendEmail(SimpleMailMessage msg);

}

