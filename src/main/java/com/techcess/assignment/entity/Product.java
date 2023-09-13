package com.techcess.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productCode;

    private String name;
    private String description;
    private Double price;
    private String category;

    @OneToMany(mappedBy = "product")
    private List<Stock> stocks;
}
