package com.techcess.assignment.service.impl;

import com.techcess.assignment.dto.request.RequestProductDTO;
import com.techcess.assignment.dto.request.RequestUpdatePeoductDTO;
import com.techcess.assignment.dto.response.ResponseProductDTO;
import com.techcess.assignment.entity.Product;
import com.techcess.assignment.repo.ProductRepo;
import com.techcess.assignment.service.ProductService;
import com.techcess.assignment.util.ErrorList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public String createProduct(RequestProductDTO requestProductDTO) {
        if(productRepo.existsById(requestProductDTO.getProductCode())){
            return ErrorList.RSP_DUPLICATED;
        }else{
            productRepo.save(modelMapper.map(requestProductDTO, Product.class));
            return ErrorList.RSP_SUCCESS;
        }
    }

    @Override
    public String updateProductQuery(RequestUpdatePeoductDTO requestUpdatePeoductDTO, Long code) {
        if(productRepo.existsByProductCode(code)){
            productRepo.updateProductQuery(
                    requestUpdatePeoductDTO.getName(),
                    requestUpdatePeoductDTO.getDescription(),
                    requestUpdatePeoductDTO.getPrice(),
                    requestUpdatePeoductDTO.getCategory(),
                    code

            );
            return ErrorList.RSP_SUCCESS;
        }else{
            return ErrorList.RSP_NO_DATA_FOUND;
        }
    }

    @Override
    public String deleteProduct(Long code) {
        if(productRepo.existsByProductCode(code)){
            productRepo.deleteById(code);
            return ErrorList.RSP_SUCCESS;
        }else{
            return ErrorList.RSP_NO_DATA_FOUND;
        }
    }

    @Override
    public List<ResponseProductDTO> getProducts() {
        List<Product> productList = productRepo.findAll();
        if(productList.size()> 0){
            List<ResponseProductDTO> productDTOS
                    = modelMapper.map(productList,new TypeToken<List<ResponseProductDTO>>(){
            }.getType());
            return productDTOS;
        }
        return null;
    }
}
