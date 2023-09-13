package com.techcess.assignment.service;

import com.techcess.assignment.dto.request.RequestStockDTO;
import com.techcess.assignment.dto.request.RequestUpdateStock;
import com.techcess.assignment.dto.response.ResponseStockDTO;

import java.util.List;

public interface StockService {


    String createStock(RequestStockDTO requestStockDTO);

    String updateStockQuery(RequestUpdateStock requestUpdateStock, int id);

    String deleteStock(int code);

    List<ResponseStockDTO> getStocks();
}
