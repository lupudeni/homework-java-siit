package com.homework.week11;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class BracketTest {
    private Bracket sut;

    @Before
    public void setUp() {
        sut = new Bracket();
    }

    @Test
    public void given_A_String_With_Symmetrical_Balanced_Brackets_When_Calling_Is_Balanced_Then_Return_True() {
        //Given
        String s = "{[()]}";

        //When
        boolean areBracketsBalanced = sut.isBalanced(s);

        //Then
        assertThat(areBracketsBalanced).isTrue();
    }

    @Test
    public void given_A_String_With_Unsymmetrical_Balanced_Brackets_When_Calling_Is_Balanced_Then_Return_True() {
        //Given
        String s = "{[()]()}";

        //When
        boolean areBracketsBalanced = sut.isBalanced(s);

        //Then
        assertThat(areBracketsBalanced).isTrue();
    }

    @Test
    public void given_A_String_With_Unbalanced_Brackets_When_Calling_Is_Balanced_Then_Return_False() {
        //Given
        String s = "{[(])}";

        //When
        boolean areBracketsBalanced = sut.isBalanced(s);

        //Then
        assertThat(areBracketsBalanced).isFalse();
    }

    @Test
    public void given_A_String_With_Balanced_Brackets_And_Other_Characters_When_Calling_Is_Balanced_Then_Return_False() {
        //Given
        String s = "abc[](){}";

        //When
        boolean areBracketsBalanced = sut.isBalanced(s);

        //Then
        assertThat(areBracketsBalanced).isFalse();
    }

    @Test
    public void given_Null_When_Calling_Is_Balanced_Then_Return_False() {
        //Given
        String s = null;

        //When
        boolean areBracketsBalanced = sut.isBalanced(s);

        //Then
        assertThat(areBracketsBalanced).isFalse();
    }

    @Test
    public void given_Empty_String_When_Calling_Is_Balanced_Then_Return_True() {
        //Given
        String s = "";

        //When
        boolean areBracketsBalanced = sut.isBalanced(s);

        //Then
        assertThat(areBracketsBalanced).isFalse();
    }

    @Test
    public void given_String_With_Inside_Out_Brackets_When_Calling_Is_Balanced_Then_Return_True() {
        //Given
        String s = "}])([{";

        //When
        boolean areBracketsBalanced = sut.isBalanced(s);

        //Then
        assertThat(areBracketsBalanced).isFalse();
    }

}
