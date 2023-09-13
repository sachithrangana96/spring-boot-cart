package com.techcess.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long historyId;

    @ManyToOne
    @JoinColumn(name="product_code",referencedColumnName = "productCode")
    private Product product;

    private Double oldPrice;
    private Double newPrice;
    private Date timeStamp;
}
