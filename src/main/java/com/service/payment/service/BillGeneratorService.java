package com.service.payment.service;

import com.service.payment.dao.dto.BillEntityDto;

public interface BillGeneratorService {

    public byte[] generateBillPdf(BillEntityDto billDto);


}
