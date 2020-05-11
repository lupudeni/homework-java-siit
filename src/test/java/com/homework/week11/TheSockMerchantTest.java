package com.homework.week11;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class TheSockMerchantTest {
    private TheSockMerchant sut;

    @Before
    public void setUp() {
        sut = new TheSockMerchant();
    }

    @Test
    public void given_Array_Of_Int_With_Pairs_When_Calling_Sock_Merchant_Then_Return_Number_Of_Pairs() {
        //Given
        int[] socks = {1, 2, 1, 2, 1, 3, 2};

        //When
        int numberOfPairs = sut.sockMerchant(socks);

        //Then
        assertThat(numberOfPairs).isEqualTo(2);
    }

    @Test
    public void given_Array_Of_Int_With_More_Pairs_Of_One_Color_When_Calling_Sock_Merchant_Then_Return_Number_Of_Pairs() {
        //Given
        int[] socks = {1, 2, 1, 2, 1, 3, 2, 1, 1, 2, 2};

        //When
        int numberOfPairs = sut.sockMerchant(socks);

        //Then
        assertThat(numberOfPairs).isEqualTo(4);
    }

    @Test
    public void given_Array_Of_Int_Without_Pairs_When_Calling_Sock_Merchant_Then_Return_Number_0() {
        //Given
        int[] socks = {1, 2, 3, 4};

        //When
        int numberOfPairs = sut.sockMerchant(socks);

        //Then
        assertThat(numberOfPairs).isEqualTo(0);
    }

    @Test
    public void given_Null_Array_When_Calling_Sock_Merchant_Then_Return_Number_0() {
        //Given
        int[] socks = null;

        //When
        int numberOfPairs = sut.sockMerchant(socks);

        //Then
        assertThat(numberOfPairs).isEqualTo(0);
    }

    @Test
    public void given_Empty_Array_When_Calling_Sock_Merchant_Then_Return_Number_0() {
        //Given
        int[] socks = {};

        //When
        int numberOfPairs = sut.sockMerchant(socks);

        //Then
        assertThat(numberOfPairs).isEqualTo(0);
    }
}
