package com.homework.firstquiz;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class TriangleTest {

    @Test
    public void given_Greater_Than_Zero_Sides_When_Creating_Triangle_Then_Object_Is_Created() {
        //Given
        double sideA = 3;
        double sideB = 4;
        double sideC = 5;

        //When
        Triangle triangle = new Triangle(sideA, sideB, sideC);

        //Then
        assertThat(triangle).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Lower_Than_Zero_Sides_When_Creating_Triangle_Then_Throws_IllegalArgumentException() {
        //Given
        double sideA = 0;
        double sideB = 4;
        double sideC = 5;

        //When
        Triangle triangle = new Triangle(sideA, sideB, sideC);

        //Then
        //It doesn't get to this
    }

}
