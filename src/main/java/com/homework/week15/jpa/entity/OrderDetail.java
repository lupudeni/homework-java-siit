package com.homework.week15.jpa.entity;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class OrderDetail {


    private int quantityOrdered;

    private BigDecimal priceEach;

    private int orderLineNumber;

    @ToString.Exclude
    private Order order;

    @ToString.Exclude
    private Product product;
}
