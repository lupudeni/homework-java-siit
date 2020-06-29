package com.homework.week15.jdbc.domain;

import lombok.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Order {

    private int orderNumber;

    private LocalDate orderDate;

    private LocalDate requiredDate;

    private LocalDate shippedDate;

    private String status;

    private String comments;

//    @ToString.Exclude
    private Customer customer;

//    @ToString.Exclude
    private List<OrderDetail> orderDetailList;

}
