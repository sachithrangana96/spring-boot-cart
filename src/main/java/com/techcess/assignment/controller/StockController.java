package com.techcess.assignment.controller;

import com.techcess.assignment.dto.request.*;
import com.techcess.assignment.dto.response.ResponseProductDTO;
import com.techcess.assignment.dto.response.ResponseStockDTO;
import com.techcess.assignment.service.StockService;
import com.techcess.assignment.util.ErrorList;
import com.techcess.assignment.util.StandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"*" })
@RequestMapping("/api/v1/stock")
public class StockController {

    @Autowired
    private StandResponse standResponse;

    @Autowired
    private StockService stockService;

    @PostMapping(path = "/")
    public ResponseEntity<RequestStockDTO> createStock(@RequestBody RequestStockDTO requestStockDTO ){

        try {
            String  response = stockService.createStock(requestStockDTO);
            if (response.equals("00")){
                standResponse.setCode(ErrorList.RSP_SUCCESS);
                standResponse.setMessage("Success");
                standResponse.setContent(requestStockDTO);
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




    @PutMapping(path = "/{id}")
    public ResponseEntity updateStock(@RequestBody RequestUpdateStock requestUpdateStock, @PathVariable(value = "id") int id){
        try{
            String response = stockService.updateStockQuery(requestUpdateStock,id);
            if (response.equals("00")){
                standResponse.setCode(ErrorList.RSP_SUCCESS);
                standResponse.setMessage("Update Successfully !..");
                standResponse.setContent(null);
                return new ResponseEntity(standResponse, HttpStatus.ACCEPTED);

            }else{
                standResponse.setCode(ErrorList.RSP_NO_DATA_FOUND);
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
    public ResponseEntity deleteStock(@RequestParam(value = "code") int code){
        try{
            String response = stockService.deleteStock(code);
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
    public ResponseEntity<StandResponse> getStocks(){
        try{
            List<ResponseStockDTO> stockDTOS = stockService.getStocks();
            standResponse.setCode(ErrorList.RSP_SUCCESS);
            standResponse.setMessage("Success");
            standResponse.setContent(stockDTOS);
            return new ResponseEntity(standResponse, HttpStatus.ACCEPTED);
        }catch (Exception e){
            standResponse.setCode(ErrorList.RSP_ERROR);
            System.out.println(e);
            standResponse.setMessage(e.getMessage());
            standResponse.setContent(null);
            return new ResponseEntity(standResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
