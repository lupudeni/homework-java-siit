package com.homework.week8;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Name: '" + name + '\'' +
                "; Age: " + age;
    }
}
