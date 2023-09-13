package com.techcess.assignment.repo;

import com.techcess.assignment.entity.Sale;
import com.techcess.assignment.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@EnableJpaRepositories
@Repository
@Transactional

public interface SaleItemRepo extends JpaRepository<SaleItem,Long> {
}
