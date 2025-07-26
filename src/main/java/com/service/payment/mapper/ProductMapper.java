package com.service.payment.mapper;

import com.service.payment.dao.dto.ProductEntityDto;
import com.service.payment.dao.model.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "totalPrice", source = "dto", qualifiedByName = "calculateTotalPrice")
    ProductEntity dtoToEntity(ProductEntityDto dto);

    @Named("calculateTotalPrice")
    default BigDecimal calculateTotalPrice(ProductEntityDto dto) {
        return dto.getPrice().multiply(BigDecimal.valueOf(dto.getAmount()));
    }

    List<ProductEntity> dtoToEntity(List<ProductEntityDto> dto);
}
