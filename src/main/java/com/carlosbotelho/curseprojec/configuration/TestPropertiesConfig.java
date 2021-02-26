package com.carlosbotelho.curseprojec.configuration;

import com.carlosbotelho.curseprojec.services.Email.EmailService;
import com.carlosbotelho.curseprojec.services.Email.MockEmailService;
import com.carlosbotelho.curseprojec.services.util.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestPropertiesConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateDatabase();
        return true;

    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }

}
