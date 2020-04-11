package com.homework.firstquiz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(MockitoJUnitRunner.class)
public class SquareTest {
    @Test
    public void given_Greater_Than_Zero_Side_When_Creating_Square_Then_Object_Is_Created() {
        //Given
        double side = 4;

        //When
        Square square = new Square(side);

        //Then
        assertThat(square).isNotNull();
    }

    @Test
    public void given_Lower_Than_Zero_Side_When_Creating_Square_Then_Throws_IllegalArgumentException() {
        //Given
        double side = -1;

        //When
        Throwable throwable = catchThrowable(
                () -> new Square(side));

        //Then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("Side must be greater than 0.");
    }

}
