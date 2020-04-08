package com.homework.firstquiz;

import com.homework.util.ActionStatus;
import com.homework.util.MyPrinter;

import java.util.ArrayList;
import java.util.List;

public class Tool {
    List<Shape> shapes;
    MyPrinter printer;

    public Tool() {
        this(new ArrayList<>(), new MyPrinter());
    }

    public Tool(List<Shape> shapes, MyPrinter printer) {
        this.shapes = shapes;
        this.printer = printer;
    }


    double calculatePerimeter(Shape shape) {
        if (shape == null) {
            return 0;
        }
        return shape.getPerimeter();
    }

    double calculateSurface(Shape shape) {
        if (shape == null) {
            return 0;
        }
        return shape.getSurface();
    }

    void addShape(Shape shape) {
        if (shape == null) {
            printer.print(ActionStatus.BAD_INPUT);
        } else if (!isShapeDuplicate(shape)) {
            shapes.add(shape);
        } else {
            printer.print("Shape has not been added because there is a similar shape already in the list.");
        }
    }

    void removeShape(Shape shape) {
        if (shapes.contains(shape)) {
            shapes.remove(shape);
        }
        else {
            printer.print(ActionStatus.NOT_FOUND);
        }
    }

    boolean isShapeDuplicate(Shape shape) {
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
