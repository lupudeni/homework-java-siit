package com.homework.week15.jpa.entity;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Employee {
    private int employeeNumber;

    private String lastName;

    private String firstName;

    private String extension;

    private String email;

    private String jobTitle;

    @ToString.Exclude
    private Employee reportsTo;

    @ToString.Exclude
    private Office office;

    @ToString.Exclude
    private List<Customer> customerList;
}
