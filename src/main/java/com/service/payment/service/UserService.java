package com.service.payment.service;

import com.service.payment.dao.dto.UserEntityRequestDto;
import com.service.payment.dao.dto.UserEntityResponseDto;
import com.service.payment.dao.model.UserEntity;

import java.math.BigDecimal;

public interface UserService {

    UserEntity getUserWithDetails(Long id);

    UserEntityResponseDto create(UserEntityRequestDto request);

    UserEntityResponseDto getById(Long id);

    BigDecimal getBalance(Long id);

    BigDecimal addMoney(Long id, BigDecimal money);

    void buyBook(Long id, BigDecimal money);

    void delete(Long id);
}
