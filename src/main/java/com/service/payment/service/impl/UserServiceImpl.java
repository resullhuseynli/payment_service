package com.service.payment.service.impl;

import com.service.payment.dao.UserEntityRepository;
import com.service.payment.dao.dto.UserEntityRequestDto;
import com.service.payment.dao.dto.UserEntityResponseDto;
import com.service.payment.dao.model.UserEntity;
import com.service.payment.exception.InsufficientMoneyException;
import com.service.payment.exception.NotFoundException;
import com.service.payment.mapper.UserMapper;
import com.service.payment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.service.payment.enums.UserStatus.ACTIVE;
import static com.service.payment.enums.UserStatus.INACTIVE;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;

    public UserEntity getUserWithDetails(Long id) {
        return userEntityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("UserNotFound"));
    }

    public UserEntityResponseDto create(UserEntityRequestDto request) {
        UserEntity userEntity = userMapper.dtoToEntity(request);
        userEntity.setStatus(ACTIVE);
        userEntity.setBalance(BigDecimal.ZERO);
        userEntity = userEntityRepository.save(userEntity);
        return userMapper.entityToDto(userEntity);
    }

    public UserEntityResponseDto getById(Long id) {
        UserEntity user = getUserWithDetails(id);
        return userMapper.entityToDto(user);
    }

    public BigDecimal getBalance(Long id) {
        return getUserWithDetails(id).getBalance();
    }

    public BigDecimal addMoney(Long id, BigDecimal money) {
        UserEntity user = getUserWithDetails(id);
        BigDecimal balance = user.getBalance();
        BigDecimal newBalance = balance.add(money);
        user.setBalance(newBalance);
        userEntityRepository.save(user);
        return balance;
    }

    public BigDecimal buyBook(Long id, BigDecimal money) {
        UserEntity user = getUserWithDetails(id);
        BigDecimal balance = user.getBalance();
        BigDecimal newBalance = balance.subtract(money);
        if (newBalance.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InsufficientMoneyException("InsufficientMoney");
        }
        user.setBalance(newBalance);
        userEntityRepository.save(user);
        return balance;
    }

    @Override
    public void delete(Long id) {
        UserEntity user = getUserWithDetails(id);
        user.setStatus(INACTIVE);
        userEntityRepository.save(user);
    }
}
