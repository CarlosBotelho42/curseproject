package com.carlosbotelho.curseprojec.configuration;

import com.carlosbotelho.curseprojec.services.Email.EmailService;
import com.carlosbotelho.curseprojec.services.Email.SmtpEmailService;
import com.carlosbotelho.curseprojec.services.util.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevPropertiesConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;
    @Bean
    public boolean instantiateDatabase() throws ParseException {

        if(!"create".equals(strategy)){
            return false;
        }
        dbService.instantiateDatabase();
        return true;

    }

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }

}
