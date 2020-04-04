package com.homework.week5.atm;

import com.homework.util.ActionStatus;
import com.homework.week5.strings.StringManipulation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.as;
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
        Card returnedCard = sut.logIn(cardNumber, pin);

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
        Card returnedCard = sut.logIn(cardNumber, pin);

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
        Card returnedCard = sut.logIn(cardNumber, pin);

        //Then
        assertThat(returnedCard).isNull();
    }

    // interrogateBalance(Card card)

    @Test
    public void given_Known_Card_When_Interrogate_Balance_Then_Return_Balance_Of_Corresponding_Account() {
        //Given
        Card card = new Card("1", "0000");
        BankAccount account = new BankAccount(new BigDecimal(100), card);

        Mockito.when(bankDataBase.getBankAccountByCardNumber(card.getNumber())).thenReturn(account);

        //When
        BigDecimal balance = sut.interrogateBalance(card);

        //Then
        assertThat(balance).isEqualByComparingTo(account.getBalance());

    }

    @Test
    public void given_Unknown_Card_When_Interrogate_Balance_Then_Return_Null() {
        //Given
        Card card = new Card("1", "0000");
        BankAccount account = new BankAccount(new BigDecimal(100), card);

        Card card2 = new Card("2", "9999");
        Mockito.when(bankDataBase.getBankAccountByCardNumber(card2.getNumber())).thenReturn(null);

        //When
        BigDecimal balance = sut.interrogateBalance(card2);

        //Then
        assertThat(balance).isNull();

    }

    // deposit(Card card, BigDecimal amount)

    @Test
    public void given_Card_And_Positive_Amount_When_Deposit_Then_Money_Is_Added_And_Return_Success_Status() {
        //Given
        Card card = new Card("1", "0000");
        BigDecimal amount = new BigDecimal(100);
        BankAccount account = new BankAccount(new BigDecimal(200), card);

        Mockito.when(bankDataBase.getBankAccountByCardNumber(card.getNumber())).thenReturn(account);

        //When
        String transactionStatus = sut.deposit(card, amount);

        //Then
        assertThat(transactionStatus).isEqualTo(ActionStatus.SUCCESS);
        assertThat(account.getBalance()).isEqualByComparingTo("300");
    }

    @Test
    public void given_Card_And_Negative_Amount_When_Deposit_Then_Money_Is_Not_Added_And_Return_Fail_Status() {
        //Given
        Card card = new Card("1", "0000");
        BigDecimal amount = new BigDecimal(-100);
        BankAccount account = new BankAccount(new BigDecimal(200), card);

        Mockito.when(bankDataBase.getBankAccountByCardNumber(card.getNumber())).thenReturn(account);

        //When
        String transactionStatus = sut.deposit(card, amount);

        //Then
        assertThat(transactionStatus).isEqualTo(ActionStatus.FAIL);
        assertThat(account.getBalance()).isEqualByComparingTo("200");
    }

    @Test
    public void given_Card_And_Zero_Amount_When_Deposit_Then_Money_Is_Not_Added_And_Return_Fail_Status() {
        //Given
        Card card = new Card("1", "0000");
        BigDecimal amount = new BigDecimal(0);
        BankAccount account = new BankAccount(new BigDecimal(200), card);

        Mockito.when(bankDataBase.getBankAccountByCardNumber(card.getNumber())).thenReturn(account);

        //When
        String transactionStatus = sut.deposit(card, amount);

        //Then
        assertThat(transactionStatus).isEqualTo(ActionStatus.FAIL);
        assertThat(account.getBalance()).isEqualByComparingTo("200");
    }

    //withdraw(Card card, BigDecimal amount)

    @Test
    public void given_Card_And_Positive_Amount_Lower_Than_Balance_When_Withdraw_Then_Money_Is_Subtracted_And_Return_Success_Status() {
        //Given
        Card card = new Card("1", "0000");
        BigDecimal amount = new BigDecimal(100);
        BankAccount account = new BankAccount(new BigDecimal(200), card);

        Mockito.when(bankDataBase.getBankAccountByCardNumber(card.getNumber())).thenReturn(account);

        //When
        String transactionStatus = sut.withdraw(card, amount);

        //Then
        assertThat(transactionStatus).isEqualTo(ActionStatus.SUCCESS);
        assertThat(account.getBalance()).isEqualByComparingTo("100");
    }

    @Test
    public void given_Card_And_Negative_Amount_Lower_Than_Balance_When_Withdraw_Then_Money_Is_Not_Subtracted_And_Return_Fail_Status() {
        //Given
        Card card = new Card("1", "0000");
        BigDecimal amount = new BigDecimal(-100);
        BankAccount account = new BankAccount(new BigDecimal(200), card);

        Mockito.when(bankDataBase.getBankAccountByCardNumber(card.getNumber())).thenReturn(account);

        //When
        String transactionStatus = sut.withdraw(card, amount);

        //Then
        assertThat(transactionStatus).isEqualTo(ActionStatus.FAIL);
        assertThat(account.getBalance()).isEqualByComparingTo("200");
    }

    @Test
    public void given_Card_And_Positive_Amount_Greater_Than_Balance_When_Withdraw_Then_Money_Is_Not_Subtracted_And_Return_Fail_Status() {
        //Given
        Card card = new Card("1", "0000");
        BigDecimal amount = new BigDecimal(300);
        BankAccount account = new BankAccount(new BigDecimal(200), card);

        Mockito.when(bankDataBase.getBankAccountByCardNumber(card.getNumber())).thenReturn(account);

        //When
        String transactionStatus = sut.withdraw(card, amount);

        //Then
        assertThat(transactionStatus).isEqualTo(ActionStatus.FAIL);
        assertThat(account.getBalance()).isEqualByComparingTo("200");
    }

    @Test
    public void given_Card_And_Zero_Amount_When_Withdraw_Then_Money_Is_Not_Subtracted_And_Return_Fail_Status() {
        //Given
        Card card = new Card("1", "0000");
        BigDecimal amount = new BigDecimal(0);
        BankAccount account = new BankAccount(new BigDecimal(200), card);

        Mockito.when(bankDataBase.getBankAccountByCardNumber(card.getNumber())).thenReturn(account);

        //When
        String transactionStatus = sut.withdraw(card, amount);

        //Then
        assertThat(transactionStatus).isEqualTo(ActionStatus.FAIL);
        assertThat(account.getBalance()).isEqualByComparingTo("200");
    }

    //changePin(Card card, String newPin)

    @Test
    public void given_Only_Digits_Length_4_When_Change_Pin_Then_Change_Pin_And_Return_Success_Status() {
        //Given
        String newPin = "1234";
        Card card = new Card("1", "0000");
        Mockito.when(stringManipulation.checkIfOnlyDigits(newPin)).thenReturn(true);

        //When
        String status = sut.changePin(card, newPin);

        //Then
        assertThat(card.getPin()).isEqualTo(newPin);
        assertThat(status).isEqualTo(ActionStatus.SUCCESS);
    }

    @Test
    public void given_Only_Digits_Length_Greater_Than_4_When_Change_Pin_Then_Pin_Is_Not_Changed_And_Return_Fail_Status() {
        //Given
        String newPin = "12345";
        Card card = new Card("1", "0000");

        //When
        String status = sut.changePin(card, newPin);

        //Then
        assertThat(card.getPin()).isEqualTo("0000");
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }

    @Test
    public void given_Only_Digits_Length_Lower_Than_4_When_Change_Pin_Then_Pin_Is_Not_Changed_And_Return_Fail_Status() {
        //Given
        String newPin = "123";
        Card card = new Card("1", "0000");

        //When
        String status = sut.changePin(card, newPin);

        //Then
        assertThat(card.getPin()).isEqualTo("0000");
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }

    @Test
    public void given_Not_Only_Digits_Length_4_When_Change_Pin_Then_Pin_Is_Not_Changed_And_Return_Fail_Status() {
        //Given
        String newPin = "abc4";
        Card card = new Card("1", "0000");
        Mockito.when(stringManipulation.checkIfOnlyDigits(newPin)).thenReturn(false);

        //When
        String status = sut.changePin(card, newPin);

        //Then
        assertThat(card.getPin()).isEqualTo("0000");
        assertThat(status).isEqualTo(ActionStatus.FAIL);
    }


}
