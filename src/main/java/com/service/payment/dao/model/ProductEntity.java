package com.service.payment.dao.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductEntity {

    private String name;
    private Integer amount;
    private BigDecimal price;
    private BigDecimal totalPrice;
}
