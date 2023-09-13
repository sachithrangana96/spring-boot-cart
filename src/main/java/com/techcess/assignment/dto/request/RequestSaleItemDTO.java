package com.techcess.assignment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestSaleItemDTO {

    private Long productCode;
    private int quantity;
    private Double totalAmount;
}
