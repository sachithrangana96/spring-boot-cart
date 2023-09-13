package com.techcess.assignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseSaleItemDTO {
    private Long itemId;
    private Long productCode;
    private String  productName;
    private int quantity;
    private Double totalAmount;
}
