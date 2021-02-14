package com.carlosbotelho.curseprojec;

import com.carlosbotelho.curseprojec.domain.Category;
import com.carlosbotelho.curseprojec.domain.City;
import com.carlosbotelho.curseprojec.domain.Product;
import com.carlosbotelho.curseprojec.domain.State;
import com.carlosbotelho.curseprojec.repositories.CategoryRepository;
import com.carlosbotelho.curseprojec.repositories.CityRepository;
import com.carlosbotelho.curseprojec.repositories.ProductRepository;
import com.carlosbotelho.curseprojec.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		
	}
}
