package com.service.payment.controller;

import com.service.payment.dao.dto.BillEntityDto;
import com.service.payment.service.BillGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2/bill")
public class BillController {

    private final BillGeneratorService billGeneratorService;

    @PostMapping
    public ResponseEntity<byte[]> create(@RequestBody BillEntityDto billEntity) {
        byte[] pdf = billGeneratorService.generateBillPdf(billEntity);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=bill.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
