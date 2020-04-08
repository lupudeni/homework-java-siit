package com.homework.firstquiz;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Rectangle implements Shape{

    private double width;
    private double length;

    public Rectangle(double width, double length) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }

    @Override
    public double getSurface() {
        return length * width;
    }
}
