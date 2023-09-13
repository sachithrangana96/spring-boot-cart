package com.techcess.assignment.service.impl;

import com.techcess.assignment.dto.QueryInterface.StockDetailsInterface;
import com.techcess.assignment.dto.request.RequestStockDTO;
import com.techcess.assignment.dto.request.RequestUpdateStock;
import com.techcess.assignment.dto.response.ResponseStockDTO;
import com.techcess.assignment.entity.Stock;
import com.techcess.assignment.repo.StockRepo;
import com.techcess.assignment.service.StockService;
import com.techcess.assignment.util.ErrorList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepo stockRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String createStock(RequestStockDTO requestStockDTO) {
        if(stockRepo.existsById(requestStockDTO.getStokId())){
            return ErrorList.RSP_DUPLICATED;
        }else{
            stockRepo.save(modelMapper.map(requestStockDTO, Stock.class));
            return ErrorList.RSP_SUCCESS;
        }
    }

    @Override
    public String updateStockQuery(RequestUpdateStock requestUpdateStock, int id) {
        if(stockRepo.existsById(id)){
            Stock stock = stockRepo.getById(id);
            stock.setAvailableQuantity(stock.getAvailableQuantity() + requestUpdateStock.getAvailableQuantity());
            return ErrorList.RSP_SUCCESS;
        }else{
            return ErrorList.RSP_NO_DATA_FOUND;
        }

    }

    @Override
    public String deleteStock(int id) {
        if(stockRepo.existsById(id)){
            stockRepo.deleteById(id);
            return ErrorList.RSP_SUCCESS;
        }else{
            return ErrorList.RSP_NO_DATA_FOUND;
        }
    }

    @Override
    public List<ResponseStockDTO> getStocks() {
        List<StockDetailsInterface> stock = stockRepo.findAllStockWithProductInfo();
        List<ResponseStockDTO> list = new ArrayList<>();
        for(StockDetailsInterface o : stock) {
            ResponseStockDTO responseStockDTO = new ResponseStockDTO(
                    o.getStokId(), o.getAvailableQuantity(), o.getProductCode(), o.getProductName()
                    );
            list.add(responseStockDTO);
        }
        return list;
    }
}
