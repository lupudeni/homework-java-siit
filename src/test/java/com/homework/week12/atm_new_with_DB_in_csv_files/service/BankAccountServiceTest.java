package com.homework.week12.atm_new_with_DB_in_csv_files.service;

import com.homework.exception.DatabaseException;
import com.homework.exception.EntityNotFoundException;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.BankAccount;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;
import com.homework.week12.atm_new_with_DB_in_csv_files.repository.BankAccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @InjectMocks
    private BankAccountService sut;

    @Test
    public void given_Known_Card_When_Interrogate_Balance_Then__Return_Balance() throws EntityNotFoundException {
        //Given
        String cardId = "1";
        Card card = Card.builder()
                .cardID(cardId)
                .pin("1234")
                .build();

        BankAccount account = BankAccount.builder()
                .accountID("1")
                .balance(new BigDecimal("4735"))
                .card(card)
                .build();
        Mockito.when(bankAccountRepository.getBankAccountByCardId(cardId)).thenReturn(Optional.of(account));

        //When
        BigDecimal result = sut.interrogateBalance(card);

        //Then
        assertThat(result).isEqualTo("4735");
    }

    @Test(expected = EntityNotFoundException.class)
    public void given_Unknown_Card_When_Interrogate_Balance_Then_Throw_Exception() throws EntityNotFoundException {
        //Given
        String cardId = "5";
        Card card = Card.builder()
                .cardID(cardId)
                .pin("1234")
                .build();
        Mockito.when(bankAccountRepository.getBankAccountByCardId(cardId)).thenReturn(Optional.empty());

        //When
        sut.interrogateBalance(card);
    }

    @Test
    public void given_Valid_Amount_When_Deposit_Then_Return_Success() throws EntityNotFoundException, DatabaseException {
        //Given
        BigDecimal amount = new BigDecimal("100");
        String cardId = "1";
        Card card = Card.builder()
                .cardID(cardId)
                .pin("1234")
                .build();
        BankAccount account = BankAccount.builder()
                .accountID("1")
                .balance(new BigDecimal("100"))
                .card(card)
                .build();

        BankAccount updatedAccount = BankAccount.builder()
                .accountID("1")
                .balance(new BigDecimal("200"))
                .card(card)
                .build();
        Mockito.when(bankAccountRepository.getBankAccountByCardId(cardId)).thenReturn(Optional.of(account));

        //When
        String result = sut.deposit(card, amount);

        //Then
        Mockito.verify(bankAccountRepository, Mockito.times(1)).update(updatedAccount);
        assertThat(result).isEqualTo("Success");
    }

    @Test
    public void given_Invalid_Amount_When_Deposit_Then_Return_Fail() throws EntityNotFoundException {
        //Given
        BigDecimal amount = new BigDecimal("-100");
        String cardId = "1";
        Card card = Card.builder()
                .cardID(cardId)
                .pin("1234")
                .build();

        //When
        String result = sut.deposit(card, amount);

        //Then
        assertThat(result).isEqualTo("Fail");
    }

    @Test
    public void given_Unknown_Path_When_Deposit_Then_Return_Fail() throws EntityNotFoundException, DatabaseException {
        //Given
        BigDecimal amount = new BigDecimal("100");
        String cardId = "1";
        Card card = Card.builder()
                .cardID(cardId)
                .pin("1234")
                .build();
        BankAccount account = BankAccount.builder()
                .accountID("1")
                .balance(new BigDecimal("100"))
                .card(card)
                .build();
        Mockito.when(bankAccountRepository.getBankAccountByCardId(cardId)).thenReturn(Optional.of(account));
        Mockito.doThrow(new DatabaseException(": Update failure")).when(bankAccountRepository).update(account);

        //When
        String result = sut.deposit(card, amount);

        //Then
        assertThat(result).isEqualTo("Fail : Update failure");
    }

    @Test
    public void given_Valid_Amount_And_Card_When_Withdraw_Then_Return_Success() throws EntityNotFoundException, DatabaseException {
        //Given
        BigDecimal amount = new BigDecimal("100");
        String cardId = "1";
        Card card = Card.builder()
                .cardID(cardId)
                .pin("1234")
                .build();
        BankAccount account = BankAccount.builder()
                .accountID("1")
                .balance(new BigDecimal("200"))
                .card(card)
                .build();
        BankAccount updatedAccount = BankAccount.builder()
                .accountID("1")
                .balance(new BigDecimal("100"))
                .card(card)
                .build();

        Mockito.when(bankAccountRepository.getBankAccountByCardId(cardId)).thenReturn(Optional.of(account));

        //When
        String result = sut.withdraw(card, amount);

        //Then
        Mockito.verify(bankAccountRepository, Mockito.times(1)).update(updatedAccount);
        assertThat(result).isEqualTo("Success");
    }

    @Test
    public void given_Invalid_Amount_And_Card_When_Withdraw_Then_Return_Fail() throws EntityNotFoundException {
        //Given
        BigDecimal amount = new BigDecimal("200");
        String cardId = "1";
        Card card = Card.builder()
                .cardID(cardId)
                .pin("1234")
                .build();
        BankAccount account = BankAccount.builder()
                .accountID("1")
                .balance(new BigDecimal("100"))
                .card(card)
                .build();
        Mockito.when(bankAccountRepository.getBankAccountByCardId(cardId)).thenReturn(Optional.of(account));

        //When
        String result = sut.withdraw(card, amount);

        //Then
        assertThat(result).isEqualTo("Fail");
    }

    @Test
    public void given_Unknown_Path_When_Withdraw_Then_Return_Fail() throws EntityNotFoundException, DatabaseException {
        //Given
        BigDecimal amount = new BigDecimal("100");
        String cardId = "1";
        Card card = Card.builder()
                .cardID(cardId)
                .pin("1234")
                .build();
        BankAccount account = BankAccount.builder()
                .accountID("1")
                .balance(new BigDecimal("200"))
                .card(card)
                .build();
        Mockito.when(bankAccountRepository.getBankAccountByCardId(cardId)).thenReturn(Optional.of(account));
        Mockito.doThrow(new DatabaseException(": Update failure")).when(bankAccountRepository).update(account);
        //When
        String result = sut.withdraw(card, amount);

        //Then
        assertThat(result).isEqualTo("Fail : Update failure");
    }

    @Test
    public void given_Negative_Amount_When_Withdraw_Then_Return_Fail() throws EntityNotFoundException {
        //Given
        BigDecimal amount = new BigDecimal("-100");
        String cardId = "1";
        Card card = Card.builder()
                .cardID(cardId)
                .pin("1234")
                .build();
        //When
        String result = sut.withdraw(card, amount);

        //Then
        assertThat(result).isEqualTo("Fail");
    }

    @Test
    public void given_Null_When_Withdraw_Then_Return_Fail() throws EntityNotFoundException {
        //When
        String result = sut.withdraw(null, null);

        //Then
        assertThat(result).isEqualTo("Fail");
    }

    @Test
    public void given_Valid_Account_When_Get_User_Id_By_Account_Then_Return_UserId() throws EntityNotFoundException {
        //Given
        Card card = Card.builder()
                .cardID("1")
                .pin("1234")
                .build();
        String accountId = "1";
        BankAccount account = BankAccount.builder()
                .accountID(accountId)
                .balance(new BigDecimal("200"))
                .card(card)
                .build();
        Mockito.when(bankAccountRepository.getUserIdByAccountId(accountId)).thenReturn("1");

        //When
        String result = sut.getUserIdByAccount(account);

        //Then
        assertThat(result).isEqualTo("1");
    }

    @Test(expected = EntityNotFoundException.class)
    public void given_Invalid_Account_When_Get_User_Id_By_Account_Then_Throw_Exception() throws EntityNotFoundException {
        //Given
        Card card = Card.builder()
                .cardID("1")
                .pin("1234")
                .build();
        String accountId = "1";
        BankAccount account = BankAccount.builder()
                .accountID(accountId)
                .balance(new BigDecimal("200"))
                .card(card)
                .build();
        Mockito.when(bankAccountRepository.getUserIdByAccountId(accountId)).thenThrow(new EntityNotFoundException("Account unknown"));

        //When
        sut.getUserIdByAccount(account);
    }

    @Test(expected = EntityNotFoundException.class)
    public void given_null_When_Get_User_Id_By_Account_Then_Throw_Exception() throws EntityNotFoundException {
        //When
        sut.getUserIdByAccount(null);
    }
}