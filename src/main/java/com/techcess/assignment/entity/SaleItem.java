package com.techcess.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "saleId")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_code", referencedColumnName = "productCode")
    private Product product;

    private int quantity;
    private Double totalAmount;

}
