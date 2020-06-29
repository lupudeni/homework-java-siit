package com.homework.week15.jdbc.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Customer {

    private int customerNumber;

    private String customerName;

    private String contactLastName;

    private String contactFirstName;

    private String phone;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String postalCode;

    private String country;

    private BigDecimal creditLimit;

//    @ToString.Exclude
    private Employee salesRepEmployee;

//    @ToString.Exclude
    private List<Order> orderList;

//    @ToString.Exclude
    private List<Payment> paymentList;

}
