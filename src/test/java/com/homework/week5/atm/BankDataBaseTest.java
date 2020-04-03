package com.homework.week5.atm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BankDataBaseTest {

    //getCardByNumber(String cardNumber)

    @Test
    public void given_Known_Card_Number_When_Get_Card_By_Card_Number_Then_Return_Card() {
        //Given
        List<Card> cards = new ArrayList<>();
        Card card = new Card("1", "0000");
        cards.add(card);

        BankDataBase sut = new BankDataBase(null, null, cards);

        String cardNumber = "1";

        //When
        Card returnedCard = sut.getCardByNumber(cardNumber);

        //Then
        assertThat(returnedCard).isEqualTo(card);
    }

    @Test
    public void given_Unknown_Card_Number_When_Get_Card_By_Card_Number_Then_Return_Null() {
        //Given
        List<Card> cards = new ArrayList<>();
        Card card = new Card("1", "0000");
        cards.add(card);

        BankDataBase sut = new BankDataBase(null, null, cards);

        String cardNumber = "2";

        //When
        Card returnedCard = sut.getCardByNumber(cardNumber);

        //Then
        assertThat(returnedCard).isNull();
    }

    @Test
    public void given_Null_Card_Number_When_Get_Card_By_Card_Number_Then_Return_Null() {
        //Given
        List<Card> cards = new ArrayList<>();
        Card card = new Card("1", "0000");
        cards.add(card);

        BankDataBase sut = new BankDataBase(null, null, cards);

        String cardNumber = null;

        //When
        Card returnedCard = sut.getCardByNumber(cardNumber);

        //Then
        assertThat(returnedCard).isNull();
    }

    //getBankAccountByCardNumber(String cardNumber)

    @Test
    public void given_Known_Card_Number_When_Get_Bank_Account_By_Card_Number_Then_Return_Card() {
        //Given
        List<Card> cards = new ArrayList<>();
        Card card = new Card("1", "0000");
        cards.add(card);

        List<BankAccount> bankAccounts = new ArrayList<>();
        BankAccount account = new BankAccount(new BigDecimal(5000), card);
        bankAccounts.add(account);

        BankDataBase sut = new BankDataBase(null, bankAccounts, cards);

        String cardNumber = "1";

        //When
        BankAccount returnedAccount = sut.getBankAccountByCardNumber(cardNumber);

        //Then
        assertThat(returnedAccount).isEqualTo(account);
    }

    @Test
    public void given_Unknown_Card_Number_When_Get_Bank_Account_By_Card_Number_Then_Return_Null() {
        //Given
        List<Card> cards = new ArrayList<>();
        Card card = new Card("1", "0000");
        cards.add(card);

        List<BankAccount> bankAccounts = new ArrayList<>();
        BankAccount account = new BankAccount(new BigDecimal(5000), card);
        bankAccounts.add(account);

        BankDataBase sut = new BankDataBase(null, bankAccounts, cards);

        String cardNumber = "2";

        //When
        BankAccount returnedAccount = sut.getBankAccountByCardNumber(cardNumber);

        //Then
        assertThat(returnedAccount).isNull();
    }

    @Test
    public void given_Null_Card_Number_When_Get_Bank_Account_By_Card_Number_Then_Return_Null() {
        //Given
        List<Card> cards = new ArrayList<>();
        Card card = new Card("1", "0000");
        cards.add(card);

        List<BankAccount> bankAccounts = new ArrayList<>();
        BankAccount account = new BankAccount(new BigDecimal(5000), card);
        bankAccounts.add(account);

        BankDataBase sut = new BankDataBase(null, bankAccounts, cards);

        String cardNumber = null;

        //When
        BankAccount returnedAccount = sut.getBankAccountByCardNumber(cardNumber);

        //Then
        assertThat(returnedAccount).isNull();
    }

    //getUserByCard(Card card)

    @Test
    public void given_Known_Card_When_Get_User_By_Card_Then_Return_User() {
        //Given
        List<Card> cards = new ArrayList<>();
        Card card = new Card("1", "0000");
        cards.add(card);

        List<BankAccount> bankAccounts = new ArrayList<>();
        BankAccount account = new BankAccount(new BigDecimal(5000), card);
        bankAccounts.add(account);

        List<User> users = new ArrayList<>();
        User user = new User("Ionut", bankAccounts, cards);
        users.add(user);

        BankDataBase sut = new BankDataBase(users, bankAccounts, cards);

        //When
        User returnedUser = sut.getUserByCard(card);

        //Then
        assertThat(returnedUser).isEqualTo(user);
    }

    @Test
    public void given_Unknown_Card_When_Get_User_By_Card_Then_Return_Null() {
        //Given
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card("1", "0000");
        cards.add(card1);

        List<BankAccount> bankAccounts = new ArrayList<>();
        BankAccount account = new BankAccount(new BigDecimal(5000), card1);
        bankAccounts.add(account);

        List<User> users = new ArrayList<>();
        User user = new User("Ionut", bankAccounts, cards);
        users.add(user);

        Card card2 = new Card("2", "9999");
        BankDataBase sut = new BankDataBase(users, bankAccounts, cards);

        //When
        User returnedUser = sut.getUserByCard(card2);

        //Then
        assertThat(returnedUser).isNull();
    }

    @Test
    public void given_Null_Card_When_Get_User_By_Card_Then_Return_Null() {
        //Given
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card("1", "0000");
        cards.add(card1);

        List<BankAccount> bankAccounts = new ArrayList<>();
        BankAccount account = new BankAccount(new BigDecimal(5000), card1);
        bankAccounts.add(account);

        List<User> users = new ArrayList<>();
        User user = new User("Ionut", bankAccounts, cards);
        users.add(user);

        Card card2 = null;
        BankDataBase sut = new BankDataBase(users, bankAccounts, cards);

        //When
        User returnedUser = sut.getUserByCard(card2);

        //Then
        assertThat(returnedUser).isNull();
    }




}
