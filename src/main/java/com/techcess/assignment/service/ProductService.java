package com.techcess.assignment.service;

import com.techcess.assignment.dto.request.RequestProductDTO;
import com.techcess.assignment.dto.request.RequestUpdatePeoductDTO;
import com.techcess.assignment.dto.response.ResponseProductDTO;

import java.util.List;

public interface ProductService {

    String createProduct(RequestProductDTO requestProductDTO);

    String updateProductQuery(RequestUpdatePeoductDTO requestUpdateStockDTO, Long code);

    String deleteProduct(Long code);

    List<ResponseProductDTO> getProducts();
}
