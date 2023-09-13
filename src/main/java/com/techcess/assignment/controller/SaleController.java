package com.techcess.assignment.controller;

import com.techcess.assignment.dto.request.RequestProductDTO;
import com.techcess.assignment.dto.request.RequestSaleDTO;
import com.techcess.assignment.dto.response.ResponseProductDTO;
import com.techcess.assignment.dto.response.ResponseSaleDTO;
import com.techcess.assignment.dto.response.ResponseSaleItemDTO;
import com.techcess.assignment.service.SaleService;
import com.techcess.assignment.util.ErrorList;
import com.techcess.assignment.util.StandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"*" })
@RequestMapping("/api/v1/Sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private StandResponse standResponse;

    @PostMapping(path = "/")
    public ResponseEntity<RequestSaleDTO> createSale(@RequestBody RequestSaleDTO requestSaleDTO){

        try {
            String  response = saleService.createSale(requestSaleDTO);
            if (response.equals("00")){
                standResponse.setCode(ErrorList.RSP_SUCCESS);
                standResponse.setMessage("Success");
                standResponse.setContent(requestSaleDTO);
                return new ResponseEntity(standResponse, HttpStatus.ACCEPTED);

            }else{
                standResponse.setCode(ErrorList.RSP_DUPLICATED);
                standResponse.setMessage("Error");
                standResponse.setContent(null);
                return new ResponseEntity(standResponse, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            standResponse.setCode(ErrorList.RSP_ERROR);
            standResponse.setMessage(e.getMessage());
            standResponse.setContent(null);
            return new ResponseEntity(standResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/sale-items")
    public ResponseEntity<StandResponse> getSaleItems(){
        try{
            List<ResponseSaleItemDTO> saleItemsDtos = saleService.getSaleItems();
            standResponse.setCode(ErrorList.RSP_SUCCESS);
            standResponse.setMessage("Success");
            standResponse.setContent(saleItemsDtos);
            return new ResponseEntity(standResponse, HttpStatus.ACCEPTED);
        }catch (Exception e){
            standResponse.setCode(ErrorList.RSP_ERROR);
            standResponse.setMessage("Error");
            standResponse.setContent(null);
            return new ResponseEntity(standResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/")
    public ResponseEntity<StandResponse> getSale(){
        try{
            List<ResponseSaleDTO> saleDtos = saleService.getSale();
            standResponse.setCode(ErrorList.RSP_SUCCESS);
            standResponse.setMessage("Success");
            standResponse.setContent(saleDtos);
            return new ResponseEntity(standResponse, HttpStatus.ACCEPTED);
        }catch (Exception e){
            standResponse.setCode(ErrorList.RSP_ERROR);
            standResponse.setMessage("Error");
            standResponse.setContent(null);
            return new ResponseEntity(standResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
