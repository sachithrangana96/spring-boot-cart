package com.techcess.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long saleId;
    private Date saleDate;
    private Double subTotal;

    @OneToMany(mappedBy = "sale",cascade = CascadeType.ALL)
    private List<SaleItem> saleItems;
}
