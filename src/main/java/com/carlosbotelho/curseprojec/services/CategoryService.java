package com.carlosbotelho.curseprojec.services;

import com.carlosbotelho.curseprojec.domain.Category;
import com.carlosbotelho.curseprojec.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Serviço capaz de atender o controlador Rest relacionado a operações com categorias
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findBy(Integer id){
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.orElse(null);
    }

}
