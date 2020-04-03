package com.homework.week5.atm;

import com.homework.week5.strings.StringManipulation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)

public class ElectronicBankingTest {
    private ElectronicBanking sut;

    @Mock
    private BankDataBase bankDataBase;

    @Mock
    private StringManipulation stringManipulation;

    @Before
    public void setup() {
        sut = new ElectronicBanking(bankDataBase, stringManipulation);
    }

    //logIn(String cardNumber, String pin)

    @Test
    public void given_Known_Card_Number_And_Valid_Pin_When_LogIn_Then_Return_Card() {
        //Given
        String cardNumber = "1";
        String pin = "0000";

        Card card = new Card("1", "0000");
        Mockito.when(bankDataBase.getCardByNumber(cardNumber)).thenReturn(card);

        //When
        Card returnedCard = sut.logIn(cardNumber,pin);

        //Then
        assertThat(returnedCard).isEqualTo(card);
    }

    @Test
    public void given_Unknown_Card_Number_And_Valid_Pin_When_LogIn_Then_Return_Null() {
        //Given
        String cardNumber = "1";
        String pin = "0000";

        Mockito.when(bankDataBase.getCardByNumber(cardNumber)).thenReturn(null);

        //When
        Card returnedCard = sut.logIn(cardNumber,pin);

        //Then
        assertThat(returnedCard).isNull();
    }

    @Test
    public void given_Known_Card_Number_And_Invalid_Pin_When_LogIn_Then_Return_Null() {
        //Given
        String cardNumber = "1";
        String pin = "9998";

        Card card = new Card("1", "0000");
        Mockito.when(bankDataBase.getCardByNumber(cardNumber)).thenReturn(card);

        //When
        Card returnedCard = sut.logIn(cardNumber,pin);

        //Then
        assertThat(returnedCard).isNull();
    }

   // interrogateBalance(Card card)



}
