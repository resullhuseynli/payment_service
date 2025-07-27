package com.service.payment.dao.entity;

import com.service.payment.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_first_name")
    private String firstName;

    @Column(name = "user_last_name")
    private String lastName;

    @Column(name = "user_email")
    private String email;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "user_balance")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus status;
}
