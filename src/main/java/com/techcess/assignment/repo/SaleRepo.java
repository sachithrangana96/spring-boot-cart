package com.techcess.assignment.repo;

import com.techcess.assignment.dto.QueryInterface.SaleDetailsInterface;
import com.techcess.assignment.entity.Sale;
import com.techcess.assignment.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@EnableJpaRepositories
@Repository
@Transactional
public interface SaleRepo extends JpaRepository<Sale,Long> {

    @Query(value = "select s.item_id as itemId,s.quantity as quantity,s.total_amount as totalAmount ," +
            "p.product_code as productCode,p.name as productName from sale_item s " +
            "Join product p on p.product_code = s.product_code",nativeQuery = true)
    List<SaleDetailsInterface> findAllSaleWithProductInfo();
}
