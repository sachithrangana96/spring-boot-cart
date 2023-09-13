package com.techcess.assignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseStockDTO {
    private int stokId;
    private int availableQuantity;
    private Long productCode;
    private String productName;
}
