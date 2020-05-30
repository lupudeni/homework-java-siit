package com.homework.week12.atm_new_with_DB_in_csv_files.controller;

import com.homework.exception.EntityNotFoundException;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.BankAccount;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.User;
import com.homework.week12.atm_new_with_DB_in_csv_files.service.BankAccountService;
import com.homework.week12.atm_new_with_DB_in_csv_files.service.CardService;
import com.homework.week12.atm_new_with_DB_in_csv_files.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class BankControllerTest {
    @Mock
    private CardService cardService;
    @Mock
    private BankAccountService bankAccountService;
    @Mock
    private UserService userService;

    @InjectMocks
    private BankController sut;

    @Test
    public void given_Valid_Id_And_Pin_When_Login_Then_Return_Card() throws LoginException {
        //Given
        String id = "1";
        String pin = "1234";
        Card card = Card.builder()
                .cardID(id)
                .pin(pin)
                .build();
        Mockito.when(cardService.logIn(id, pin)).thenReturn(card);

        //When
        Card result = sut.logIn(id, pin);

        //Then
        assertThat(result).isEqualTo(card);
    }

    @Test(expected = LoginException.class)
    public void given_Invalid_Id_And_Pin_When_Login_Then_Return_Card() throws LoginException {
        //Given
        String id = "1";
        String pin = "1234";
        Mockito.when(cardService.logIn(id, pin)).thenThrow(new LoginException("Login failed"));

        //When
        sut.logIn(id, pin);
    }

    @Test
    public void given_Valid_Card_And_Pin_When_Change_Pin_Then_Return_Success() {
        //Given
        String id = "1";
        String pin = "1234";
        Card card = Card.builder()
                .cardID(id)
                .pin(pin)
                .build();
        String newPin = "0000";
        Mockito.when(cardService.changePin(card, newPin)).thenReturn("Success");

        //When
        String result = sut.changePin(card, newPin);

        //Then
        assertThat(result).isEqualTo("Success");
    }

    @Test
    public void given_Invalid_Card_And_Pin_When_Change_Pin_Then_Return_Fail() {
        //Given
        String id = "1";
        String pin = "1234";
        Card card = Card.builder()
                .cardID(id)
                .pin(pin)
                .build();
        String newPin = "abc";
        Mockito.when(cardService.changePin(card, newPin)).thenReturn("Fail");

        //When
        String result = sut.changePin(card, newPin);

        //Then
        assertThat(result).isEqualTo("Fail");
    }

    @Test
    public void given_Valid_Card_When_Interrogate_Balance_Then_Return_Balance() throws EntityNotFoundException {
        //Given
        BigDecimal balance = new BigDecimal("100");
        String id = "1";
        String pin = "1234";
        Card card = Card.builder()
                .cardID(id)
                .pin(pin)
                .build();
        Mockito.when(bankAccountService.interrogateBalance(card)).thenReturn(balance);

        //When
        String result = sut.interrogateBalance(card);

        //Then
        assertThat(result).isEqualTo("100");
    }

    @Test
    public void given_Invalid_Card_When_Interrogate_Balance_Then_Return_Fail() throws EntityNotFoundException {
        //Given
        String id = "1";
        String pin = "1234";
        Card card = Card.builder()
                .cardID(id)
                .pin(pin)
                .build();
        Mockito.when(bankAccountService.interrogateBalance(card)).thenThrow(new EntityNotFoundException("No card found for card id '" + id + "'"));

        //When
        String result = sut.interrogateBalance(card);

        //Then
        assertThat(result).isEqualTo("Fail - No card found for card id '1'");
    }

    @Test
    public void given_Valid_Card_And_Balance_When_Deposit_Then_Return_Success() throws EntityNotFoundException {
        //Given
        String id = "1";
        String pin = "1234";
        Card card = Card.builder()
                .cardID(id)
                .pin(pin)
                .build();
        BigDecimal amount = new BigDecimal("100");
        Mockito.when(bankAccountService.deposit(card, amount)).thenReturn("Success");

        //When
        String result = sut.deposit(card, amount);

        //Then
        assertThat(result).isEqualTo("Success");
    }

    @Test
    public void given_Invalid_Card_And_Balance_When_Deposit_Then_Return_Fail() throws EntityNotFoundException {
        //Given
        String id = "1";
        String pin = "1234";
        Card card = Card.builder()
                .cardID(id)
                .pin(pin)
                .build();
        BigDecimal amount = new BigDecimal("100");
        Mockito.when(bankAccountService.deposit(card, amount)).thenThrow(new EntityNotFoundException("No card found for card id '" + id + "'"));

        //When
        String result = sut.deposit(card, amount);

        //Then
        assertThat(result).isEqualTo("Fail - No card found for card id '1'");
    }

    @Test
    public void given_Valid_Card_And_Balance_When_Withdraw_Then_Return_Success() throws EntityNotFoundException {
        //Given
        String id = "1";
        String pin = "1234";
        Card card = Card.builder()
                .cardID(id)
                .pin(pin)
                .build();
        BigDecimal amount = new BigDecimal("100");
        Mockito.when(bankAccountService.withdraw(card, amount)).thenReturn("Success");

        //When
        String result = sut.withdraw(card, amount);

        //Then
        assertThat(result).isEqualTo("Success");
    }

    @Test
    public void given_Invalid_Card_And_Balance_When_Withdraw_Then_Return_Fail() throws EntityNotFoundException {
        //Given
        String id = "1";
        String pin = "1234";
        Card card = Card.builder()
                .cardID(id)
                .pin(pin)
                .build();
        BigDecimal amount = new BigDecimal("100");
        Mockito.when(bankAccountService.withdraw(card, amount)).thenThrow(new EntityNotFoundException("No card found for card id '" + id + "'"));

        //When
        String result = sut.withdraw(card, amount);

        //Then
        assertThat(result).isEqualTo("Fail - No card found for card id '1'");
    }

    @Test
    public void given_Invalid_Balance_When_Withdraw_Then_Return_Fail() {
        //Given
        String id = "1";
        String pin = "1234";
        Card card = Card.builder()
                .cardID(id)
                .pin(pin)
                .build();
        BigDecimal amount = new BigDecimal("-100");

        //When
        String result = sut.withdraw(card, amount);

        //Then
        assertThat(result).isEqualTo("Fail");
    }

    @Test
    public void given_Valid_Id_When_Get_User_Name_Then_Return_Name() throws EntityNotFoundException {
        //Given
        String userId = "1";
        String userName = "name lastName";
        User user = User.builder()
                .userId(userId)
                .name(userName)
                .build();
        String cardId = "1";
        String pin = "1234";
        Card card = Card.builder()
                .cardID(cardId)
                .pin(pin)
                .build();
        BankAccount account = BankAccount.builder()
                .accountID("1")
                .balance(new BigDecimal("100"))
                .card(card)
                .build();
        Mockito.when(bankAccountService.getBankAccountByCardId(cardId)).thenReturn(account);
        Mockito.when(bankAccountService.getUserIdByAccount(account)).thenReturn(userId);
        Mockito.when(userService.getUserById(userId)).thenReturn(user);

        //When
        String result = sut.getUserName(cardId);

        //Then
        assertThat(result).isEqualTo(userName);
    }

    @Test
    public void given_Invalid_Id_When_Get_User_Name_Then_Return_Fail() throws EntityNotFoundException {
        //Given
        String cardId = "1";
        Mockito.when(bankAccountService.getBankAccountByCardId(cardId)).thenThrow(new EntityNotFoundException("No account found"));

        //When
        String result = sut.getUserName(cardId);

        //Then
        assertThat(result).isEqualTo("Fail - No account found");
    }

}