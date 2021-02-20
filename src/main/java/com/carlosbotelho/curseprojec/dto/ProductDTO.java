package com.carlosbotelho.curseprojec.dto;

import com.carlosbotelho.curseprojec.domain.Category;
import com.carlosbotelho.curseprojec.domain.Product;

import java.io.Serializable;

public class ProductDTO implements Serializable {

    private Integer id;
    private String name;
    private Double price;

    public ProductDTO(){

    }

    public ProductDTO(Product obj){
        this.id = obj.getId();
        this.name = obj.getName();
        this.price = obj.getPrice();
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
