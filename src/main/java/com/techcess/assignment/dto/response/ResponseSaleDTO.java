package com.techcess.assignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseSaleDTO {
    private Long saleId;
    private Date saleDate;
    private Double subTotal;
}
