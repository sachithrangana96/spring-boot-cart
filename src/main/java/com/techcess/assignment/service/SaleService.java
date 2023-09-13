package com.techcess.assignment.service;

import com.techcess.assignment.dto.request.RequestSaleDTO;
import com.techcess.assignment.dto.response.ResponseSaleDTO;
import com.techcess.assignment.dto.response.ResponseSaleItemDTO;

import java.util.List;

public interface SaleService {
    String createSale(RequestSaleDTO requestSaleDTO);

    List<ResponseSaleItemDTO> getSaleItems();

    List<ResponseSaleDTO> getSale();
}
