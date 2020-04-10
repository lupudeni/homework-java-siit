package com.homework.firstquiz;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Triangle implements Shape {
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        if (sideA > 0 && sideB > 0 && sideC > 0) {
            this.sideA = sideA;
            this.sideB = sideB;
            this.sideC = sideC;
        } else {
            throw new IllegalArgumentException("All sides must be greater than 0.");
        }
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public double getSurface() {
        double p = (getPerimeter() / 2);
        double calculus = p * (p - sideA) * (p - sideB) * (p - sideC);
        return Math.sqrt(calculus);
    }
}
