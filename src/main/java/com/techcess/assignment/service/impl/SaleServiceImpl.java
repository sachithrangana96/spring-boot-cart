package com.techcess.assignment.service.impl;

import com.techcess.assignment.dto.QueryInterface.SaleDetailsInterface;
import com.techcess.assignment.dto.QueryInterface.StockDetailsInterface;
import com.techcess.assignment.dto.request.RequestSaleDTO;
import com.techcess.assignment.dto.request.RequestSaleItemDTO;
import com.techcess.assignment.dto.response.ResponseProductDTO;
import com.techcess.assignment.dto.response.ResponseSaleDTO;
import com.techcess.assignment.dto.response.ResponseSaleItemDTO;
import com.techcess.assignment.dto.response.ResponseStockDTO;
import com.techcess.assignment.entity.Product;
import com.techcess.assignment.entity.Sale;
import com.techcess.assignment.entity.SaleItem;
import com.techcess.assignment.repo.ProductRepo;
import com.techcess.assignment.repo.SaleItemRepo;
import com.techcess.assignment.repo.SaleRepo;
import com.techcess.assignment.service.SaleService;
import com.techcess.assignment.util.ErrorList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepo saleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SaleItemRepo saleItemRepo;

    @Override
    @Transactional
    public String createSale(RequestSaleDTO requestSaleDTO) {
        Sale sale = new Sale();
        sale.setSaleDate(requestSaleDTO.getSaleDate());
        sale.setSubTotal(requestSaleDTO.getSubTotal());
        saleRepo.save(sale);


        if (saleRepo.existsById(sale.getSaleId())) {
            List<RequestSaleItemDTO> saleItemDTOs = requestSaleDTO.getSaleItems();
            List<SaleItem> saleItems = new ArrayList<>();

            for (RequestSaleItemDTO saleItemDTO : saleItemDTOs) {
                SaleItem saleItem = new SaleItem();
                Product product = productRepo.getById(saleItemDTO.getProductCode());

                // Assuming SaleItemDTO contains the necessary information to create a SaleItem
                saleItem.setSale(sale);
                saleItem.setProduct(product);
                saleItem.setQuantity(saleItemDTO.getQuantity());
                saleItem.setTotalAmount(saleItemDTO.getTotalAmount());
                // Set other properties of the saleItem from saleItemDTO

                saleItems.add(saleItem);
            }

            if (!saleItems.isEmpty()) {
                // Use the appropriate repository for SaleItem to save the saleItems
                saleItemRepo.saveAll(saleItems); // Assuming saleItemRepo is your SaleItem repository
            }
            return ErrorList.RSP_SUCCESS;
        }
        return ErrorList.RSP_ERROR;

    }

    @Override
    public List<ResponseSaleItemDTO> getSaleItems() {
        List<SaleDetailsInterface> saleItemsDtos = saleRepo.findAllSaleWithProductInfo();
        List<ResponseSaleItemDTO> list = new ArrayList<>();
        for(SaleDetailsInterface o : saleItemsDtos) {
            ResponseSaleItemDTO responseSaleItemDTO = new ResponseSaleItemDTO(
                    o.getItemId(), o.getProductCode(), o.getProductName(), o.getQuantity(),o.getTotalAmount()
            );
            list.add(responseSaleItemDTO);
        }
        return list;
    }

    @Override
    public List<ResponseSaleDTO> getSale() {
        List<Sale> sales = saleRepo.findAll();
        if(sales.size()> 0){
            List<ResponseSaleDTO> saleDTOS
                    = modelMapper.map(sales,new TypeToken<List<ResponseSaleDTO>>(){
            }.getType());
            return saleDTOS;
        }
        return null;
    }
}

