package com.homework.week15.jdbc.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Payment {

    private String checkNumber;

    private LocalDate paymentDate;

    private BigDecimal amount;

    @ToString.Exclude
    private Customer customer;
}
