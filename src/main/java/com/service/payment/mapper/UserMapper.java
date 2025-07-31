package com.service.payment.mapper;

import com.service.payment.dao.dto.UserEntityRequestDto;
import com.service.payment.dao.dto.UserEntityResponseDto;
import com.service.payment.dao.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntityResponseDto entityToDto(UserEntity userEntity);
    UserEntity dtoToEntity(UserEntityRequestDto request);
}
