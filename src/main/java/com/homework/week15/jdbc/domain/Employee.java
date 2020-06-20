package com.homework.week15.jdbc.domain;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Employee {
    private int employeeNumber;

    private String lastName;

    private String firstName;

    private String extension;

    private String email;

    private String jobTitle;

    @ToString.Exclude
    private Employee reportsTo;
//    private int reportsTo; // also an employee -> should make Employee? should exclude from toString?

    @ToString.Exclude
    private Office office;

    @ToString.Exclude
    private List<Customer> customerList;

//    private String officeCode;
}