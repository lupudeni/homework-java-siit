package com.homework.firstquiz;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Rectangle implements Shape{

    private double width;
    private double length;

    public Rectangle(double width, double length) {
        if (length > 0 && width > 0) {
            this.length = length;
            this.width = width;
        } else  {
            throw new IllegalArgumentException("Width and length must be greater than 0.");
        }
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
