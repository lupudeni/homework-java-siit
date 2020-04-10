package com.homework.firstquiz;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Circle implements Shape{

    private double radius;
    public Circle(double radius) {
        if (radius > 0) {
            this.radius = radius;
        } else {
            throw new IllegalArgumentException("Radius must be greater than 0.");
        }
    }

    @Override
    public double getPerimeter() {
        return  2 * Math.PI * radius;
    }

    @Override
    public double getSurface() {
        return Math.PI * Math.pow(radius, 2);
    }
}
