package com.techcess.assignment.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int stokId;

    private int availableQuantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_code",referencedColumnName = "productCode")
    private Product product;


}
