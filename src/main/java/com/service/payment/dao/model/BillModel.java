package com.service.payment.dao.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BillModel {

    private Long id;
    private String companyName;
    private String companyAddress;
    private List<ProductModel> productList;
    private BigDecimal totalPrice;
    private LocalDateTime createdDate;
}
