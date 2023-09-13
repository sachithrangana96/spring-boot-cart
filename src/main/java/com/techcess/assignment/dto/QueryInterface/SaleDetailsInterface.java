package com.techcess.assignment.dto.QueryInterface;

import java.util.Date;

public interface SaleDetailsInterface {
     Long getItemId();
     Long getProductCode();
     String getProductName();
     int getQuantity();
     double getTotalAmount();
}
