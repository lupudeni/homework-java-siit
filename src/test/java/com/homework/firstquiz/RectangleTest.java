package com.homework.firstquiz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)

public class RectangleTest {
    @Test
    public void given_Greater_Than_Zero_Side_When_Creating_Rectangle_Then_Object_Is_Created() {
        //Given
        double width = 4;
        double length = 8;


        //When
        Rectangle rectangle = new Rectangle(width, length);

        //Then
        assertThat(rectangle).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Lower_Than_Zero_Radius_When_Creating_Rectangle_Then_Throws_IllegalArgumentException() {
        //Given
        double width = 0;
        double length = 8;

        //When
        Rectangle rectangle = new Rectangle(width, length);

        //Then
        //It doesn't get to this
    }

}
