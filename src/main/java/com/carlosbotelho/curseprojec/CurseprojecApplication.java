package com.carlosbotelho.curseprojec;

import com.carlosbotelho.curseprojec.domain.*;
import com.carlosbotelho.curseprojec.domain.enums.ClientRole;
import com.carlosbotelho.curseprojec.domain.enums.PaymentStatus;
import com.carlosbotelho.curseprojec.domain.item.OrderItem;
import com.carlosbotelho.curseprojec.domain.payment.CardPayment;
import com.carlosbotelho.curseprojec.domain.payment.Payment;
import com.carlosbotelho.curseprojec.domain.payment.TicketPayment;
import com.carlosbotelho.curseprojec.repositories.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CurseprojecApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CurseprojecApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
