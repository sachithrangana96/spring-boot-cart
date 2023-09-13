package com.techcess.assignment.controller;

import com.techcess.assignment.dto.request.RequestProductDTO;
import com.techcess.assignment.dto.request.RequestUpdatePeoductDTO;
import com.techcess.assignment.dto.response.ResponseProductDTO;
import com.techcess.assignment.service.ProductService;
import com.techcess.assignment.util.ErrorList;
import com.techcess.assignment.util.StandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"*" })
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StandResponse standResponse;

    @PostMapping(path = "/")
    public ResponseEntity<ResponseProductDTO> createProduct(@RequestBody RequestProductDTO requestProductDTO){

        try {
            String  response = productService.createProduct(requestProductDTO);
            if (response.equals("00")){
                standResponse.setCode(ErrorList.RSP_SUCCESS);
                standResponse.setMessage("Success");
                standResponse.setContent(requestProductDTO);
                return new ResponseEntity(standResponse, HttpStatus.ACCEPTED);

            }else{
                standResponse.setCode(ErrorList.RSP_DUPLICATED);
                standResponse.setMessage("Error");
                standResponse.setContent(null);
                return new ResponseEntity(standResponse, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            standResponse.setCode(ErrorList.RSP_ERROR);
            standResponse.setMessage("Error");
            standResponse.setContent(null);
            return new ResponseEntity(standResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(path = "/{code}")
    public ResponseEntity<ResponseProductDTO> updateProduct(@RequestBody  RequestUpdatePeoductDTO requestUpdatePeoductDTO, @PathVariable(value = "code") Long code){
        try{
            String response = productService.updateProductQuery(requestUpdatePeoductDTO,code);
            if (response.equals("00")){
                standResponse.setCode(ErrorList.RSP_SUCCESS);
                standResponse.setMessage("Success");
                standResponse.setContent(requestUpdatePeoductDTO);
                return new ResponseEntity(standResponse, HttpStatus.ACCEPTED);

            }else{
                standResponse.setCode(ErrorList.RSP_DUPLICATED);
                standResponse.setMessage("Error");
                standResponse.setContent(null);
                return new ResponseEntity(standResponse, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            standResponse.setCode(ErrorList.RSP_ERROR);
            standResponse.setMessage("Error");
            standResponse.setContent(null);
            return new ResponseEntity(standResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @DeleteMapping( path = "/{code}")
    public ResponseEntity deleteProduct(@RequestParam(value = "code") Long code){
        try{
            String response = productService.deleteProduct(code);
            if (response.equals("00")) {
                standResponse.setCode(ErrorList.RSP_SUCCESS);
                standResponse.setMessage("Success");
                standResponse.setContent(null);
                return new ResponseEntity(standResponse, HttpStatus.ACCEPTED);
            } else {
                standResponse.setCode(ErrorList.RSP_NO_DATA_FOUND);
                standResponse.setMessage("No Product Available For this code");
                standResponse.setContent(null);
                return new ResponseEntity(standResponse, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            standResponse.setCode(ErrorList.RSP_ERROR);
            standResponse.setMessage("Error");
            standResponse.setContent(null);
            return new ResponseEntity(standResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/")
    public ResponseEntity<StandResponse> getProducts(){
        try{
            List<ResponseProductDTO> productDTO = productService.getProducts();
            standResponse.setCode(ErrorList.RSP_SUCCESS);
            standResponse.setMessage("Success");
            standResponse.setContent(productDTO);
            return new ResponseEntity(standResponse, HttpStatus.ACCEPTED);
        }catch (Exception e){
            standResponse.setCode(ErrorList.RSP_ERROR);
            standResponse.setMessage("Error");
            standResponse.setContent(null);
            return new ResponseEntity(standResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
