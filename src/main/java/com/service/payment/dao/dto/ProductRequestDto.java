package com.service.payment.dao.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductRequestDto {

    private String name;
    private Integer amount;
    private BigDecimal price;
}
