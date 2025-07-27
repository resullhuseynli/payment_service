package com.service.payment.dao.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductModel {

    private String name;
    private Integer amount;
    private BigDecimal price;
    private BigDecimal totalPrice;
}
