package com.carlosbotelho.curseprojec.services;

import com.carlosbotelho.curseprojec.domain.Category;
import com.carlosbotelho.curseprojec.domain.Client;
import com.carlosbotelho.curseprojec.dto.CategoryDTO;
import com.carlosbotelho.curseprojec.repositories.CategoryRepository;
import com.carlosbotelho.curseprojec.services.exceptions.DataIntegrityViolation;
import com.carlosbotelho.curseprojec.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Serviço capaz de atender o controlador Rest relacionado a operações com categorias
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category findBy(Integer id){
        Optional<Category> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotfoundException(
                "Objeto não encontrado! Id: "
                + id
                + ", Tipo: "
                + Category.class.getName()
        ));
    }

   public Category insert(Category obj){
        obj.setId(null);
        return repo.save(obj);
   }

    public Category update(Category obj){
        Category newObj = findBy(obj.getId());
        updateData(newObj, obj);
        return  repo.save(newObj);
    }

    private void updateData(Category newObj, Category obj) {
        newObj.setName(obj.getName());
    }

    public void delete(Integer id){
        findBy(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolation("Não é possível excluir uma categoria que tenha produtos!");
        }
    }

    public List<Category> findAll(){
        return repo.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Category fromDto(CategoryDTO objDto){
        return new Category(objDto.getId(), objDto.getName());
    }

}
