package com.service.payment.dao.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BillEntityDto {

    private String companyName;
    private String companyAddress;
    private List<ProductEntityDto> productList;
    private LocalDateTime createdDate;
}
