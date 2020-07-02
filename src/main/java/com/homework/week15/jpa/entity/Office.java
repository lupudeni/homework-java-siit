package com.homework.week15.jpa.entity;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Office {
    private String officeCode;

    private String city;

    private String phone;

    private String addressLine1;

    private String addressLine2;

    private String state;

    private String country;

    private String postalCode;

    private String territory;

    @ToString.Exclude
    private List<Employee> employeeList;
}
