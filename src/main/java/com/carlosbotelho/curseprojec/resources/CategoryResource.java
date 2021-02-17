package com.carlosbotelho.curseprojec.resources;

import com.carlosbotelho.curseprojec.domain.Category;
import com.carlosbotelho.curseprojec.dto.CategoryDTO;
import com.carlosbotelho.curseprojec.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping (value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id){
        Category obj = categoryService.findBy(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO objDto){
        Category obj = categoryService.fromDto(objDto);
        obj = categoryService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO objDto, @PathVariable Integer id){
        Category obj = categoryService.fromDto(objDto);
        obj.setId(id);
        obj = categoryService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<Category> list = categoryService.findAll();
        List<CategoryDTO> listDto = list.stream()
                .map(obj -> new CategoryDTO(obj))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoryDTO>> findPage(

           @RequestParam(value ="page", defaultValue ="0") Integer page,
           @RequestParam(value ="linesPerPage", defaultValue ="24") Integer linesPerPage,
           @RequestParam(value ="orderBy", defaultValue ="name") String orderBy,
           @RequestParam(value ="direction", defaultValue ="ASC") String direction){

        Page<Category> list = categoryService.findPage(page, linesPerPage, orderBy, direction );
        Page<CategoryDTO> listDto = list.map(obj -> new CategoryDTO(obj));

        return ResponseEntity.ok().body(listDto);
    }

}
