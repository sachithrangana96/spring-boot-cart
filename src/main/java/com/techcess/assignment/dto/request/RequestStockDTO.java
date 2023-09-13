package com.techcess.assignment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestStockDTO {
    private int stokId;
    private int availableQuantity;
    private Long productCode;
}
