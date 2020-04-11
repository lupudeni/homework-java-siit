package com.homework.firstquiz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(MockitoJUnitRunner.class)

public class RectangleTest {
    @Test
    public void given_Greater_Than_Zero_Width_And_Length_When_Creating_Rectangle_Then_Object_Is_Created() {
        //Given
        double width = 4;
        double length = 8;


        //When
        Rectangle rectangle = new Rectangle(width, length);

        //Then
        assertThat(rectangle).isNotNull();
    }

    @Test
    public void given_Lower_Than_Zero_Width_And_Length_When_Creating_Rectangle_Then_Throws_IllegalArgumentException() {
        //Given
        double width = 0;
        double length = 8;

        //When
       Throwable throwable = catchThrowable(
               () -> new Rectangle(width, length));

        //Then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("Width and length must be greater than 0.");
    }

}
