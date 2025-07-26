package com.service.payment.controller;

import com.service.payment.dao.dto.UserEntityRequestDto;
import com.service.payment.dao.dto.UserEntityResponseDto;
import com.service.payment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserEntityResponseDto> create(@RequestBody UserEntityRequestDto request) {
        return ResponseEntity.ok(userService.create(request));
    }

    @PostMapping("/{id}")
    public ResponseEntity<BigDecimal> addMoney(@PathVariable Long id, @RequestBody BigDecimal money) {
        return ResponseEntity.ok(userService.addMoney(id, money));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntityResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getBalance(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
