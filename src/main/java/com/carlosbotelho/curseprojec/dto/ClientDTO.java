package com.carlosbotelho.curseprojec.dto;

import com.carlosbotelho.curseprojec.domain.Client;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ClientDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Valor obrigatório!")
    @Length(min = 5, max = 80, message = "Tamanho deve ser entre 5 a 80 caracteres")
    private String name;

    @Email(message = "Email invalido")
    @NotEmpty(message = "Valor obrigatório!")
    private String email;

    public ClientDTO(){

    }

    public ClientDTO(Client obj){
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
