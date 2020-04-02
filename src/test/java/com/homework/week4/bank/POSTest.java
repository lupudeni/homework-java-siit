package com.homework.week4.bank;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)

public class POSTest {

    private POS sut;

    @Before
    public void setup() {
        sut = new POS();
    }

    //Integration tests recommended by "OOD dev guidelines for homework"

    @Test
    public void given_Payment_At_POS_When_Balance_is_Greater_Than_Amount_Then_Money_Is_Withdrawn_And_Return_Success_Receipt() {
        //Given
        Card card = new Card("1234", LocalDate.of(2021, 5, 30), "Barky");
        BankAccount account = new BankAccount("RO 1234");
        account.attachCard("1234");
        account.setBalance(new BigDecimal(200));
        sut.addKnownBankAccount(account);
        BigDecimal amount = new BigDecimal(100);

        //When
        String receipt = sut.pay(amount, card);

        //Then
        assertThat(receipt).isEqualTo("Success");
        assertThat(account.getBalance()).isEqualByComparingTo(new BigDecimal(100));
    }

    @Test
    public void given_Payment_At_POS_When_Balance_is_Lower_Than_Amount_Then_Money_Is_Not_Withdrawn_And_Return_Fail_Receipt() {
        //Given
        Card card = new Card("1234", LocalDate.of(2021, 5, 30), "Barky");
        BankAccount account = new BankAccount("RO 1234");
        account.attachCard("1234");
        account.setBalance(new BigDecimal(100));
        BigDecimal initialBalance = account.getBalance();
        sut.addKnownBankAccount(account);
        BigDecimal amount = new BigDecimal(200);

        //When
        String receipt = sut.pay(amount, card);

        //Then
        assertThat(receipt).isEqualTo("Fail");
        assertThat(initialBalance).isEqualByComparingTo(account.getBalance());
    }

    @Test
    public void given_Payment_At_POS_When_Expiration_Date_Is_Before_Current_Date_Then_Money_Is_Not_Withdrawn_And_Return_Fail_Receipt() {
        //Given
        Card card = new Card("1234", LocalDate.of(2019, 7, 30), "Barky");
        BankAccount account = new BankAccount("RO 1234");
        account.attachCard("1234");
        account.setBalance(new BigDecimal(200));
        BigDecimal initialBalance = account.getBalance();
        sut.addKnownBankAccount(account);
        BigDecimal amount = new BigDecimal(100);

        //When
        String receipt = sut.pay(amount, card);

        //Then
        assertThat(receipt).isEqualTo("Fail");
        assertThat(initialBalance).isEqualByComparingTo(account.getBalance());
    }

    @Test
    public void given_Payment_At_POS_When_POS_Does_Not_Know_Bank_Account_Then_Money_Is_Not_Withdrawn_And_Return_Fail_Receipt() {
        //Given
        Card card = new Card("1234", LocalDate.of(2023, 9, 30), "Barky");
        BankAccount account = new BankAccount("RO 1234");
        account.attachCard("1234");
        account.setBalance(new BigDecimal(200));
        BigDecimal initialBalance = account.getBalance();
        BigDecimal amount = new BigDecimal(100);

        //When
        String receipt = sut.pay(amount, card);

        //Then
        assertThat(receipt).isEqualTo("Fail");
        assertThat(initialBalance).isEqualByComparingTo(account.getBalance());

    }

    //Unit tests

    //BankAccount getBankAccountByCard(Card card)

    @Test
    public void given_Unknown_Bank_Account_When_Get_Bank_Account_By_Card_Then_Return_Null() {
        //Given
        Card card1 = new Card("1234", LocalDate.of(2023, 9, 30), "Barky");
        BankAccount account1 = new BankAccount("RO 1234");
        account1.attachCard("1234");
        sut.addKnownBankAccount(account1);

        Card card2 = new Card("0000", LocalDate.of(2024, 10, 30), "Fluffy");
        BankAccount account2 = new BankAccount("RO 0000");

        //When
        BankAccount returnedAccount = sut.getBankAccountByCard(card2);

        //Then
        assertThat(returnedAccount).isNull();
    }

    //String pay(BigDecimal amount, Card card)

    @Test
    public void given_Null_Bank_Account_When_Pay_Then_Money_Is_Not_Withdrawn_And_Return_Fail_Receipt() {
        //Given
        Card card1 = new Card("1234", LocalDate.of(2023, 9, 30), "Barky");
        BankAccount account1 = new BankAccount("RO 1234");
        account1.attachCard("1234");
        sut.addKnownBankAccount(account1);

        Card card2 = new Card("0000", LocalDate.of(2024, 10, 30), "Fluffy");
        BankAccount account2 = new BankAccount("RO 0000");
        account2.setBalance(new BigDecimal(100));
        BigDecimal initialBalance = account2.getBalance();

        BigDecimal amount = new BigDecimal(50);

        //When
        String receipt = sut.pay(amount, card2);

        //Then
        assertThat(receipt).isEqualTo("Fail");
        assertThat(initialBalance).isEqualByComparingTo(account2.getBalance());


    }
}
