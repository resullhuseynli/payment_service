package com.service.payment.service;

import com.service.payment.dao.dto.BillRequestDto;

public interface BillGeneratorService {

    byte[] generateBillPdf(BillRequestDto billDto);


}
