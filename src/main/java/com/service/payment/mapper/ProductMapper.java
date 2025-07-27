package com.service.payment.mapper;

import com.service.payment.dao.dto.ProductRequestDto;
import com.service.payment.dao.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "totalPrice", source = "dto", qualifiedByName = "calculateTotalPrice")
    ProductModel dtoToEntity(ProductRequestDto dto);

    @Named("calculateTotalPrice")
    default BigDecimal calculateTotalPrice(ProductRequestDto dto) {
        return dto.getPrice().multiply(BigDecimal.valueOf(dto.getAmount()));
    }

    List<ProductModel> dtoToEntity(List<ProductRequestDto> dto);
}
