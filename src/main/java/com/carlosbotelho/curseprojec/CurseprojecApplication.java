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

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");

		Product prod1 = new Product(null,"Notebook", 5000.00);
		Product prod2 = new Product(null,"Impressora", 950.00);
		Product prod3 = new Product(null,"Mouse", 120.00);

		cat1.getProducts().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProducts().addAll(Arrays.asList(prod2));

		prod1.getCategories().addAll(Arrays.asList(cat1));
		prod2.getCategories().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1,cat2));
		productRepository.saveAll(Arrays.asList(prod1, prod2, prod3));

		State s1 = new State(null, "Minas Gerais");
		State s2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", s1);
		City c2 = new City(null, "São Paulo", s2);
		City c3 = new City(null, "Campinas", s2);

		stateRepository.saveAll(Arrays.asList(s1,s2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silvia", "maria@gmail.com", "3499854784", ClientRole.PESSOAFISICA);

		cli1.getPhones().addAll(Arrays.asList("92587549", "92856331"));

		Address a1 = new Address(null, "Rua Flores", "300", "Apto 301", "Jadim", "89489464", cli1, c1);
		Address a2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "7546464", cli1, c2);

		cli1.getAddresses().addAll(Arrays.asList(a1, a2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(a1,a2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Order ped1 = new Order(null, sdf.parse("30/09/2020 13:58"),a1, cli1);
		Order ped2 = new Order(null, sdf.parse("25/10/2020 12:40"),a2, cli1);

		Payment pag1 = new CardPayment(null,  PaymentStatus.QUITADO, ped1,6 );
		ped1.setPayment(pag1);

		Payment pag2 = new TicketPayment(null,  PaymentStatus.PENDENTE, ped2, sdf.parse("30/10/2020 00:00"),null );
		ped2.setPayment(pag2);

		cli1.getOrders().addAll(Arrays.asList(ped1,ped2));

		orderRepository.saveAll(Arrays.asList(ped1, ped2));
		paymentRepository.saveAll(Arrays.asList(pag1,pag2));

		OrderItem oi1 = new OrderItem(prod1, ped1, 0.00,1, 5000.00);
		OrderItem oi2 = new OrderItem(prod3, ped1, 0.00,2, 240.00);
		OrderItem oi3 = new OrderItem(prod2, ped2, 100.00,1, 950.00);

		ped1.getItems().addAll(Arrays.asList(oi1, oi2));
		ped2.getItems().addAll(Arrays.asList(oi3));

		prod1.getItems().addAll(Arrays.asList(oi1));
		prod2.getItems().addAll(Arrays.asList(oi3));
		prod3.getItems().addAll(Arrays.asList(oi2));

		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));

	}
}
