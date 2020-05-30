package com.homework.week12.atm_new_with_DB_in_csv_files.service;

import com.homework.exception.DatabaseException;
import com.homework.exception.EntityNotFoundException;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;
import com.homework.week12.atm_new_with_DB_in_csv_files.repository.CardRepository;
import com.homework.week5.strings.StringManipulation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;
    @Mock
    private StringManipulation stringManipulation;
    @InjectMocks
    private CardService sut;

    @Test
    public void given_ID_When_Get_Card_By_Id_Then_Return_Card() throws EntityNotFoundException {
        //Given
        String cardId = "1";
        Card card = Card.builder()
                .cardID(cardId)
                .pin("1234")
                .build();
        Mockito.when(cardRepository.getCardById(cardId)).thenReturn(Optional.of(card));

        //When
        Card result = sut.getCardByID(cardId);

        //Then
        assertThat(result).isEqualTo(card);
    }

    @Test(expected = EntityNotFoundException.class)
    public void given_Unknown_ID_When_Get_Card_By_Id_Then_Throw_Exception() throws EntityNotFoundException {
        //Given
        String cardId = "2";
        Mockito.when(cardRepository.getCardById(cardId)).thenReturn(Optional.empty());

        //When
        sut.getCardByID(cardId);
    }

    @Test(expected = EntityNotFoundException.class)
    public void given_Null_When_Get_Card_By_Id_Then_Throw_Exception() throws EntityNotFoundException {
        //Given
        String cardId = null;
        Mockito.when(cardRepository.getCardById(cardId)).thenReturn(Optional.empty());

        //When
        sut.getCardByID(cardId);
    }

    @Test
    public void given_Correct_Id_And_Pin_When_LogIn_Then_Return_Card() throws LoginException {
        //Given
        String id = "1";
        String pin = "1234";
        Card card = Card.builder()
                .cardID(id)
                .pin(pin)
                .build();

        Mockito.when(cardRepository.getCardById(id)).thenReturn(Optional.of(card));

        //When
        Card result = sut.logIn(id, pin);

        //Then
        assertThat(result).isEqualTo(card);
    }

    @Test(expected = LoginException.class)
    public void given_Incorrect_Id_And_Pin_When_LogIn_Then_Throw_Exception() throws LoginException {
        //Given
        String id = "2";
        String pin = "1234";

        Mockito.when(cardRepository.getCardById(id)).thenReturn(Optional.empty());

        //When
        Card result = sut.logIn(id, pin);
    }

    @Test(expected = LoginException.class)
    public void given_Correct_Id_And_Incorrect_Pin_When_LogIn_Then_Throw_Exception() throws LoginException {
        //Given
        String id = "1";
        String pin = "1234";
        String wrongPin = "0000";
        Card card = Card.builder()
                .cardID(id)
                .pin(pin)
                .build();

        Mockito.when(cardRepository.getCardById(id)).thenReturn(Optional.of(card));

        //When
        Card result = sut.logIn(id, wrongPin);
    }

    @Test
    public void given_Known_Card_And_Valid_Pin_When_Change_Pin_Then_Return_Success() throws DatabaseException {
        //Given
        Card card = Card.builder()
                .cardID("1")
                .pin("1234")
                .build();
        String newPin = "0000";
        Card updatedCard = Card.builder()
                .cardID("1")
                .pin(newPin)
                .build();
        Mockito.when(cardRepository.cardExists(card)).thenReturn(true);
        Mockito.when(stringManipulation.checkIfOnlyDigits(newPin)).thenReturn(true);

        //When
        String result = sut.changePin(card, newPin);

        //Then
        Mockito.verify(cardRepository, Mockito.times(1)).update(updatedCard);
        assertThat(result).isEqualTo("Success");
    }

    @Test
    public void given_Known_Card_And_Invalid_Pin_When_Change_Pin_Then_Return_Fail() {
        //Given
        Card card = Card.builder()
                .cardID("1")
                .pin("1234")
                .build();
        String newPin = "abc";

        Mockito.when(cardRepository.cardExists(card)).thenReturn(true);

        //When
        String result = sut.changePin(card, newPin);

        //Then
        assertThat(result).isEqualTo("Fail");
    }

    @Test
    public void given_Unknown_Card_And_Pin_When_Change_Pin_Then_Return_Fail() {
        //Given
        Card card = Card.builder()
                .cardID("5")
                .pin("0000")
                .build();
        String newPin = "1234";

        Mockito.when(cardRepository.cardExists(card)).thenReturn(false);

        //When
        String result = sut.changePin(card, newPin);

        //Then
        assertThat(result).isEqualTo("Fail");
    }

    @Test
    public void given_Unknown_Path_When_Change_Pin_Then_Return_Fail() throws DatabaseException {
        //Given
        Card card = Card.builder()
                .cardID("1")
                .pin("1234")
                .build();
        String newPin = "0000";

        Mockito.when(cardRepository.cardExists(card)).thenReturn(true);
        Mockito.when(stringManipulation.checkIfOnlyDigits(newPin)).thenReturn(true);
        Mockito.doThrow(new DatabaseException(": Update failure")).when(cardRepository).update(card);

        //When
        String result = sut.changePin(card, newPin);

        //Then
        assertThat(result).isEqualTo("Fail : Update failure");
    }
}