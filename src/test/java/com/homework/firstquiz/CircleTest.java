package com.homework.firstquiz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(MockitoJUnitRunner.class)
public class CircleTest {
    @Test
    public void given_Greater_Than_Zero_Radius_When_Creating_Circle_Then_Object_Is_Created() {
        //Given
        double radius = 3;

        //When
        Circle circle = new Circle(radius);

        //Then
        assertThat(circle).isNotNull();
    }

    @Test
    public void given_Lower_Than_Zero_Radius_When_Creating_Circle_Then_Throws_IllegalArgumentException() {
        //Given
        double radius = -1;

        //When
        Throwable throwable = catchThrowable(
                () -> new Circle(radius));

        //Then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("Radius must be greater than 0.");
    }

}
