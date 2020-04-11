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

    @Test
    public void given_Lower_Than_Zero_Sides_When_Creating_Triangle_Then_Throws_IllegalArgumentException() {
        //Given
        double sideA = 0;
        double sideB = 4;
        double sideC = 5;

        //When
        Throwable thrown = catchThrowable(
                ()-> new Triangle(sideA, sideB, sideC));

        //Then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
        assertThat(thrown).hasMessage("All sides must be greater than 0.");

    }

}
