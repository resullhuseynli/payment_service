package com.service.payment.service.impl;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.service.payment.dao.dto.BillRequestDto;
import com.service.payment.dao.model.BillModel;
import com.service.payment.mapper.BillMapper;
import com.service.payment.service.BillGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class BillGeneratorServiceImpl implements BillGeneratorService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final TemplateEngine templateEngine;
    private final BillMapper billMapper;

    @Override
    @SneakyThrows
    public byte[] generateBillPdf(BillRequestDto billDto) {
        BillModel bill = billMapper.dtoToEntity(billDto);
        Context context = new Context();
        context.setVariable("companyName", bill.getCompanyName());
        context.setVariable("createdDate", bill.getCreatedDate());
        context.setVariable("productList", bill.getProductList());
        context.setVariable("totalPrice", bill.getTotalPrice());
        context.setVariable("companyAddress", bill.getCompanyAddress());

        String htmlContent = templateEngine.process("bill", context);
        return generateFromHtml(htmlContent);
    }

    @SneakyThrows
    private byte[] generateFromHtml(String html) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(html, null);
        builder.toStream(outputStream);
        builder.run();
        return outputStream.toByteArray();
    }
}
