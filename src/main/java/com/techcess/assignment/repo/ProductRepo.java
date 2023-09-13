package com.techcess.assignment.repo;

import com.techcess.assignment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@EnableJpaRepositories
@Repository
@Transactional

public interface ProductRepo extends JpaRepository<Product,Long> {



    boolean existsByProductCode(Long productCode);


    @Modifying
    @Query(value = "UPDATE product SET name = ?1, description = ?2, price = ?3, category = ?4 WHERE product_code = ?5", nativeQuery = true)
    void updateProductQuery(String name, String description, double price, String category, Long code);




}
