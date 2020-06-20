package com.homework.week15.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {


    private int quantityOrdered;

    private BigDecimal priceEach;

    private int orderLineNumber;

    @ToString.Exclude
    private Order order;
//    private int orderNumber;

    @ToString.Exclude
    private Product product;
//    private String productCode;
}
