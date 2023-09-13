package com.techcess.assignment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestProductDTO {
    private Long productCode;
    private String name;
    private String description;
    private Double price;
    private String category;
}
