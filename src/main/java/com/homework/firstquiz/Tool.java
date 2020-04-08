package com.homework.firstquiz;

import com.homework.util.MyPrinter;

import java.util.ArrayList;
import java.util.List;

public class Tool {

    List<Shape> shapes = new ArrayList<>();
    MyPrinter printer = new MyPrinter();

    double calculatePerimeter(Shape shape) {
        return shape.getPerimeter();
    }

    double calculateSurface(Shape shape) {
        return shape.getSurface();
    }

    void addShape(Shape shape) {
        if (isShapeUnique(shape)) {
            shapes.add(shape);
        } else {
            printer.print("Shape has not been added because there is a similar shape already in the list.");
        }
    }

    void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    boolean isShapeUnique(Shape shape) {
        return shapes.contains(shape);
    }

    double calculateTotalPerimeter() {
        double totalPerimeter = 0;
        for (Shape shape : shapes) {
            totalPerimeter += calculatePerimeter(shape);
        }
        return totalPerimeter;
    }

    double calculateTotalSurface() {
        double totalSurface = 0;
        for (Shape shape : shapes) {
            totalSurface += calculateSurface(shape);
        }
        return totalSurface;
    }


}
