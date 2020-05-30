package com.homework.week12.atm_new_with_DB_in_csv_files.service;

import com.homework.exception.EntityNotFoundException;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;
import com.homework.week12.atm_new_with_DB_in_csv_files.repository.CardRepository;
import com.homework.week5.strings.StringManipulation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.*;

public class CardServiceTest {
    @Mock
    private CardRepository cardRepository;

    private static final Path TEST_ROOT_PATH = Paths.get("src", "test", "java", "com", "homework", "week12", "io_test_root");
    private CardService sut;
    private Path testCardDb;
    private StringManipulation stringManipulation = new StringManipulation();

    @Before
    public void createTestingFiles() throws IOException {
        testCardDb = Files.createFile(TEST_ROOT_PATH.resolve("cardDB.csv"));
        createCardDB();
        cardRepository = new CardRepository(testCardDb);
        sut = new CardService(cardRepository, stringManipulation);
    }

    public void createCardDB() throws IOException {
        String db = "cardID,cardPin\n" +
                "1,1234\n";
        Files.write(testCardDb, db.getBytes());
    }

//    @After
    public void cleanUp() throws IOException {
        Files.deleteIfExists(testCardDb);
    }

    @Test
    public void given_ID_When_Get_Card_By_Id_Then_Return_Card() throws EntityNotFoundException {
        //Given
        Card card = Card.builder()
                .cardID("1")
                .pin("1234")
                .build();

        //When
        Card result = sut.getCardByID("1");

        //Then
        assertThat(result).isEqualTo(card);
    }

    @Test(expected = EntityNotFoundException.class)
    public void given_Unknown_ID_When_Get_Card_By_Id_Then_Throw_Exception() throws EntityNotFoundException {
        //When
        Card result = sut.getCardByID("2");
    }

    @Test(expected = EntityNotFoundException.class)
    public void given_Empty_When_Get_Card_By_Id_Then_Throw_Exception() throws EntityNotFoundException {
        //When
        Card result = sut.getCardByID("");
    }

    @Test(expected = EntityNotFoundException.class)
    public void given_Null_When_Get_Card_By_Id_Then_Throw_Exception() throws EntityNotFoundException {
        //When
        Card result = sut.getCardByID(null);
    }

    @Test
    public void given_Correct_Id_And_Pin_When_LogIn_Then_Return_Card() throws LoginException {
        //Given
        Card card = Card.builder()
                .cardID("1")
                .pin("1234")
                .build();
        String id = "1";
        String pin = "1234";

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

        //When
        Card result = sut.logIn(id, pin);
    }

    @Test(expected = LoginException.class)
    public void given_Correct_Id_And_Incorrect_Pin_When_LogIn_Then_Throw_Exception() throws LoginException {
        //Given
        String id = "1";
        String pin = "0000";

        //When
        Card result = sut.logIn(id, pin);
    }

    @Test
    public void given_Known_Card_And_Valid_Pin_When_Change_Pin_Then_Change_Pin_In_File_And_Return_Success() throws IOException {
        //Given
        Card card = Card.builder()
                .cardID("1")
                .pin("1234")
                .build();
        String newPin = "0000";

        //When
        String result = sut.changePin(card, newPin);

        //Then
        assertThat(result).isEqualTo("Success");
        assertThat(Files.lines(testCardDb)
                .skip(1)
                .map(line -> line.split(","))
                .anyMatch(line -> line[0].equals("1") && line[1].equals("0000")))
                .isTrue();
    }

    @Test
    public void given_Known_Card_And_Invalid_Pin_When_Change_Pin_Then_Do_Not_Change_Pin_In_File_And_Return_Fail() throws IOException {
        //Given
        Card card = Card.builder()
                .cardID("1")
                .pin("1234")
                .build();
        String newPin = "abc";

        //When
        String result = sut.changePin(card, newPin);

        //Then
        assertThat(result).isEqualTo("Fail");
        assertThat(Files.lines(testCardDb)
                .skip(1)
                .map(line -> line.split(","))
                .anyMatch(line -> line[0].equals("1") && line[1].equals("1234")))
                .isTrue();
    }

    @Test
    public void given_Unknown_Card_And_Pin_When_Change_Pin_Then_Do_Not_Change_Pin_In_File_And_Return_Fail() throws IOException {
        //Given
        Card card = Card.builder()
                .cardID("5")
                .pin("0000")
                .build();
        String newPin = "1234";

        //When
        String result = sut.changePin(card, newPin);

        //Then
        assertThat(result).isEqualTo("Fail");
        assertThat(Files.lines(testCardDb)
                .skip(1)
                .map(line -> line.split(","))
                .anyMatch(line -> line[0].equals("1") && line[1].equals("1234")))
                .isTrue();
    }

    @Test
    public void given_Unknown_Path_When_Change_Pin_Then_Return_Fail() {
        //Given
        Card card = Card.builder()
                .cardID("1")
                .pin("1234")
                .build();
        String newPin = "0000";

        Path unknown = TEST_ROOT_PATH.resolve("x");

        //When
       String result = newCardService.changePin(card, newPin);

        //Then
        assertThat(result).isEqualTo("");
    }




}