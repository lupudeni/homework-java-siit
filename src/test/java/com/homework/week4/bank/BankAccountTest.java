package com.homework.week4.bank;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)

public class BankAccountTest {
    private BankAccount sut;

    @Before
    public void setup() {
        sut = new BankAccount("0000");
        sut.setBalance(new BigDecimal(100));
    }

    //addMoney(BigDecimal amount)

    @Test
    public void given_Positive_Amount_When_Add_Money_Then_Amount_Is_Added_And_Return_Success_Receipt() {
        //Given
        BigDecimal amount = new BigDecimal(100);


        //When
        String receipt = sut.addMoney(amount);

        //then
        assertThat(receipt).isEqualTo("Success");
        assertThat(sut.getBalance()).isEqualByComparingTo(new BigDecimal(200));

    }

    @Test
    public void given_Negative_Amount_When_Add_Money_Then_Amount_Is_Not_Added_And_Return_Fail_Receipt() {
        //Given
        BigDecimal amount = new BigDecimal(-100);

        //When
        String receipt = sut.addMoney(amount);

        //Then
        assertThat(receipt).isEqualTo("Fail");
        assertThat(sut.getBalance()).isEqualByComparingTo(new BigDecimal(100));
    }
}
