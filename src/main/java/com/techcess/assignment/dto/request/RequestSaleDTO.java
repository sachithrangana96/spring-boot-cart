package com.techcess.assignment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestSaleDTO {
    private Date saleDate;
    private Double subTotal;
    private List<RequestSaleItemDTO> saleItems;
}
