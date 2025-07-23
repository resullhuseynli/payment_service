package com.service.payment.dao.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntityResponseDto {

    private String firstName;
    private String lastName;
    private BigDecimal balance;
}
