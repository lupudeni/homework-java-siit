package com.homework.week15.jdbc.domain;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Product {

    private String productCode; //primary key

    private String productName;

//    private String productLine; //foreign key

    private String productScale;

    private String productVendor;

    private String productDescription;

    private int quantityInStock;

    private BigDecimal buyPrice;

    private BigDecimal MSRP;

//    private OrderDetail orderDetail;

    @ToString.Exclude
    private ProductLine productLine;
}
