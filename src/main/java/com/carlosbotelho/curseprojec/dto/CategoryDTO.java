package com.carlosbotelho.curseprojec.dto;

import com.carlosbotelho.curseprojec.domain.Category;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Valor obrigatório!")
    @Length(min = 5, max = 50, message = "Tamanho deve ser entre 5 a 50 caracteres")
    private String name;

    public CategoryDTO(){

    }

    public CategoryDTO(Category obj){
        id = obj.getId();
        name = obj.getName();
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
}
