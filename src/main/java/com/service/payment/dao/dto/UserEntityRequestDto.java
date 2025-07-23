package com.service.payment.dao.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntityRequestDto {


    private String firstName;
    private String lastName;

    @Email(message = "EmailRequired")
    private String email;
}
