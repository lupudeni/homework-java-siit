package com.homework.firstquiz;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Square implements Shape {

    private double side;

    public Square(double side) {
        if (side > 0) {
            this.side = side;
        } else {
            throw new IllegalArgumentException("Side must be greater than 0.");
        }
    }

    @Override
    public double getPerimeter() {
        return side * 4;
    }

    @Override
    public double getSurface() {
        return Math.pow(side, 2);
    }
}
