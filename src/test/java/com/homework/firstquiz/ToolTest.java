package com.homework.firstquiz;

import com.homework.util.ActionStatus;
import com.homework.util.MyPrinter;
import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)

public class ToolTest {

    private Tool sut;
    List<Shape> shapes = new ArrayList<>();

    @Mock
    private MyPrinter printer;

    @Before
    public void setup() {

        sut = new Tool(shapes, printer);
    }

    //addShape(Shape shape)
    @Test
    public void given_Unique_Shape_When_Add_Shape_Then_Shape_Is_Added_To_List() {
        //Given
        Shape square = new Square(1.0);

        //When
        sut.addShape(square);

        //Then
        assertThat(shapes).contains(square);

    }

    @Test
    public void given_Unique_Shape_And_A_List_With_Various_Shapes_When_Add_Shape_Then_Shape_Is_Added_To_List() {
        //Given
        Shape square1 = new Square(1.0);
        Shape circle1 = new Circle(2.2);
        Shape triangle1 = new Triangle(3, 4, 5);
        Shape rectangle1 = new Rectangle(2.5, 5);
        Shape square2 = new Square(4.5);
        Shape triangle2 = new Triangle(1, 1, 1);
        Shape circle2 = new Circle(7);

        shapes.add(square1);
        shapes.add(circle1);
        shapes.add(triangle1);
        shapes.add(rectangle1);
        shapes.add(square2);
        shapes.add(triangle2);
        shapes.add(circle2);

        Shape rectangle2 = new Rectangle(8,7);

        //When
        sut.addShape(rectangle2);

        //Then
        assertThat(shapes).contains(rectangle2);

    }

    @Test
    public void given_Duplicate_Shape_When_Add_Shape_Then_Shape_Is_Not_Added_To_List_And_Error_Message_Is_Printed() {
        //Given
        Shape square1 = new Square(1.0);
        shapes.add(square1);
        Shape square2 = new Square(1.0);

        //When
        sut.addShape(square2);

        //Then
        assertThat(shapes).containsOnly(square1);
        Mockito.verify(printer, times(1)).print("Shape has not been added because there is a similar shape already in the list.");
    }

    @Test
    public void given_Null_Shape_When_Add_Shape_Then_Shape_Is_Not_Added_To_List_And_Error_Message_Is_Printed() {
        //Given
        Shape square = null;

        //When
        sut.addShape(square);

        //Then
        assertThat(shapes).isEmpty();
        Mockito.verify(printer, times(1)).print(ActionStatus.BAD_INPUT);
    }

    //removeShape(Shape shape)

    @Test
    public void given_Known_Shape_When_Remove_Shape_Then_Shape_Is_Removed_From_List() {
        //Given
        Shape square = new Square(1.0);

        shapes.add(square);

        //When
        sut.removeShape(square);

        //Then
        assertThat(shapes).isEmpty();
    }

    @Test
    public void given_Unknown_Shape_When_Remove_Shape_Then_Nothing_Is_Removed_From_List_And_Error_Message_Is_Printed() {
        //Given
        Shape square = new Square(1.0);
        shapes.add(square);
        Shape circle = new Circle(2.2);

        //When
        sut.removeShape(circle);

        //Then
        assertThat(shapes).contains(square);
        Mockito.verify(printer, times(1)).print(ActionStatus.NOT_FOUND);

    }

    //calculatePerimeter(Shape shape)

    //Square
    @Test
    public void given_Square_Shape_When_Calculate_Perimeter_Then_Return_Perimeter_Of_Square() {
        //Given
        Shape square = new Square(2.0);

        //When
        double p = sut.calculatePerimeter(square);

        //Then
        assertThat(p).isEqualTo(8.0);
    }

    //Rectangle
    @Test
    public void given_Rectangle_Shape_When_Calculate_Perimeter_Then_Return_Perimeter_Of_Rectangle() {
        //Given
        Shape rectangle = new Rectangle(2.5, 5);

        //When
        double p = sut.calculatePerimeter(rectangle);

        //Then
        assertThat(p).isEqualTo(15.0);
    }

    //Triangle
    @Test
    public void given_Triangle_Shape_When_Calculate_Perimeter_Then_Return_Perimeter_Of_Triangle() {
        //Given
        Shape triangle = new Triangle(3, 4, 5);

        //When
        double p = sut.calculatePerimeter(triangle);

        //Then
        assertThat(p).isEqualTo(12.0);
    }

    //Circle
    @Test
    public void given_Circle_Shape_When_Calculate_Perimeter_Then_Return_Perimeter_Of_Circle() {
        //Given
        Shape circle = new Circle(2);

        //When
        double p = sut.calculatePerimeter(circle);

        //Then
        assertThat(p).isEqualTo(12.56, Offset.strictOffset(0.01));
    }

    //Null
    @Test
    public void given_Null_Shape_When_Calculate_Perimeter_Then_Return_0() {
        //Given
        Shape circle = null;

        //When
        double p = sut.calculatePerimeter(circle);

        //Then
        assertThat(p).isEqualTo(0);
    }

   // calculateSurface(Shape shape)
   //Square
   @Test
   public void given_Square_Shape_When_Calculate_Surface_Then_Return_Surface_Of_Square() {
       //Given
       Shape square = new Square(2.0);

       //When
       double p = sut.calculateSurface(square);

       //Then
       assertThat(p).isEqualTo(4.0);
   }

    //Rectangle
    @Test
    public void given_Rectangle_Shape_When_Calculate_Surface_Then_Return_Surface_Of_Rectangle() {
        //Given
        Shape rectangle = new Rectangle(2.5, 5);

        //When
        double p = sut.calculateSurface(rectangle);

        //Then
        assertThat(p).isEqualTo(12.5);
    }

    //Triangle
    @Test
    public void given_Triangle_Shape_When_Calculate_Surface_Then_Return_SurfaceOf_Triangle() {
        //Given
        Shape triangle = new Triangle(3, 4, 5);

        //When
        double p = sut.calculateSurface(triangle);

        //Then
        assertThat(p).isEqualTo(6.0);
    }

    //Circle
    @Test
    public void given_Circle_Shape_When_Calculate_Surface_Then_Return_Surface_Of_Circle() {
        //Given
        Shape circle = new Circle(3);

        //When
        double p = sut.calculateSurface(circle);

        //Then
        assertThat(p).isEqualTo(28.27, Offset.strictOffset(0.01));
    }

    //Null
    @Test
    public void given_Null_Shape_When_Calculate_Surface_Then_Return_0() {
        //Given
        Shape circle = null;

        //When
        double p = sut.calculateSurface(circle);

        //Then
        assertThat(p).isEqualTo(0);
    }

    //calculateTotalPerimeter()

    @Test
    public void given_List_With_Various_Shapes_When_Calculate_Total_Perimeter_then_Return_Sum_Of_Perimeters() {
        //Given
        Shape square = new Square(2);
        Shape circle = new Circle(2);
        Shape triangle = new Triangle(3, 4, 5);

        shapes.add(square);
        shapes.add(circle);
        shapes.add(triangle);

        //When
        double totalP = sut.calculateTotalPerimeter();

        //Then
        assertThat(totalP).isEqualTo(32.56, Offset.strictOffset(0.01));
    }

    @Test
    public void given_Empty_List_When_Calculate_Total_Perimeter_then_Return_0() {
        //Given
        // Nothing is added to the list
        //When
        double totalP = sut.calculateTotalPerimeter();

        //Then
        assertThat(totalP).isEqualTo(0);
    }

    //calculateTotalSurface()

    @Test
    public void given_List_With_Various_Shapes_When_Calculate_Total_Surface_then_Return_Sum_Of_Surfaces() {
        //Given
        Shape square = new Square(2);
        Shape circle = new Circle(3);
        Shape triangle = new Triangle(3, 4, 5);

        shapes.add(square);
        shapes.add(circle);
        shapes.add(triangle);

        //When
        double totalP = sut.calculateTotalSurface();

        //Then
        assertThat(totalP).isEqualTo(38.27, Offset.strictOffset(0.01));
    }

    @Test
    public void given_Empty_List_When_Calculate_Total_Surface_then_Return_0() {
        //Given
        // Nothing is added to the list
        //When
        double totalP = sut.calculateTotalSurface();

        //Then
        assertThat(totalP).isEqualTo(0);
    }

}

