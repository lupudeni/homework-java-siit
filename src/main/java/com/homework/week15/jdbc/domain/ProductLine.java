package com.homework.week15.jdbc.domain;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductLine {
    private String productLine;

    private String textDescription;

    private String htmlDescription;

    private byte[] image; //mediumblob

    @ToString.Exclude
    private List<Product> productList;

}
