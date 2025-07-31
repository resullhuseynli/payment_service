package com.service.payment.mapper;

import com.service.payment.dao.dto.BillRequestDto;
import com.service.payment.dao.model.BillModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface BillMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalPrice", source = "dto", qualifiedByName = "calculateTotalPrice")
    BillModel dtoToEntity(BillRequestDto dto);

    @Named("calculateTotalPrice")
    default BigDecimal calculateTotalPrice(BillRequestDto dto) {
        return dto.getProductList().stream()
                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
