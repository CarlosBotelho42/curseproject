package com.carlosbotelho.curseprojec.resources;

import com.carlosbotelho.curseprojec.domain.Category;
import com.carlosbotelho.curseprojec.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping (value = "/categories")
public class CategoryResource {


    private CategoryService categoryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable Integer id){
        Category obj = categoryService.findBy(id);
        return ResponseEntity.ok().body(obj);
    }

}
