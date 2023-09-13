package com.techcess.assignment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestUpdatePeoductDTO {
    private String name;
    private String description;
    private Double price;
    private String category;
}
