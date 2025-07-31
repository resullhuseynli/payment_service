package com.service.payment.dao.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BillRequestDto {

    private Long id;
    private String companyName;
    private String companyAddress;
    private List<ProductRequestDto> productList;
    private LocalDateTime createdDate;
}
