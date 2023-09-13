package com.techcess.assignment.repo;

import com.techcess.assignment.dto.QueryInterface.StockDetailsInterface;
import com.techcess.assignment.entity.Product;
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
public interface StockRepo extends JpaRepository<Stock,Integer> {

    @Query(value = "select s.stok_id as stokId,s.product_code as productCode," +
            "s.available_quantity as availableQuantity,p.name as productName from stock s " +
            "Join product p on p.product_code = s.product_code",nativeQuery = true)
    List<StockDetailsInterface> findAllStockWithProductInfo();
}
