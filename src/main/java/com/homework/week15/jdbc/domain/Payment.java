package com.homework.week15.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Payment {

    private String checkNumber;

    private LocalDate paymentDate;

    private BigDecimal amount;

    @ToString.Exclude
    private Customer customer;
//    private int customerNumber;
}
