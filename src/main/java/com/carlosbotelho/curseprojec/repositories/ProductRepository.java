package com.carlosbotelho.curseprojec.repositories;

import com.carlosbotelho.curseprojec.domain.Category;
import com.carlosbotelho.curseprojec.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Essa camada da aplicação vai ser responsavel por acessar e fazer a consulta nas tabelas do banco
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //A essa consulta precisa ser criada, pos não tem disponivel no Spring Data(consulta diferenciada requer JPQL)
    //@Transactional(readOnly=true)
    //@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat IN :categories")
    //Page<Product> search(@Param("name") String ame,@Param("categories") List<Category> categories, Pageable pageRequest);

    @Transactional(readOnly = true)
    Page<Product> findDistinctByNameContainingAndCategoriesIn(String ame, List<Category> categories, Pageable pageRequest);

}
