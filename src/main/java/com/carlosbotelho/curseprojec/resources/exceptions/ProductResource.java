package com.carlosbotelho.curseprojec.resources.exceptions;

import com.carlosbotelho.curseprojec.domain.Category;
import com.carlosbotelho.curseprojec.domain.Order;
import com.carlosbotelho.curseprojec.domain.Product;
import com.carlosbotelho.curseprojec.dto.CategoryDTO;
import com.carlosbotelho.curseprojec.dto.ProductDTO;
import com.carlosbotelho.curseprojec.resources.util.URL;
import com.carlosbotelho.curseprojec.services.OrderService;
import com.carlosbotelho.curseprojec.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id){
        Product obj = service.findBy(id);

        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findPage(

            @RequestParam(value ="name", defaultValue ="") String name,
            @RequestParam(value ="categories", defaultValue ="") String categories,
            @RequestParam(value ="page", defaultValue ="0") Integer page,
            @RequestParam(value ="linesPerPage", defaultValue ="24") Integer linesPerPage,
            @RequestParam(value ="orderBy", defaultValue ="name") String orderBy,
            @RequestParam(value ="direction", defaultValue ="ASC") String direction){

        String nameDecoded = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categories);
        Page<Product> list = service.search(nameDecoded, ids, page, linesPerPage, orderBy, direction );
        Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));

        return ResponseEntity.ok().body(listDto);
    }

}
