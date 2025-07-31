package com.service.payment.service;

import com.service.payment.exception.InsufficientMoneyException;
import com.service.payment.exception.NotFoundException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final UserService userService;

    public PaymentService(KafkaTemplate<String, String> kafkaTemplate, UserService userService) {
        this.kafkaTemplate = kafkaTemplate;
        this.userService = userService;
    }

    @KafkaListener(topics = "payment-request", groupId = "payment-service")
    public void handleRequest(String message) {
        String[] parts = message.split(":");
        Long userId = Long.parseLong(parts[0]);
        String requestId = parts[1];
        BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(parts[2]));
        StringBuilder response = new StringBuilder(userId.toString());
        response.append(":").append(requestId);
        try {
            userService.buyBook(userId, amount);
        } catch (InsufficientMoneyException insufficientMoneyException) {
            response.append(":FAILURE");
            kafkaTemplate.send("payment-response", String.valueOf(response));
            System.err.println("Insufficient money exception occurred");
        } catch (NotFoundException notFoundException) {
            response.append(":FAILURE");
            kafkaTemplate.send("payment-response", String.valueOf(response));
            System.err.println("Not Found exception occurred");
        }
        response.append(":SUCCESS");
        kafkaTemplate.send("payment-response", String.valueOf(response));
    }
}
