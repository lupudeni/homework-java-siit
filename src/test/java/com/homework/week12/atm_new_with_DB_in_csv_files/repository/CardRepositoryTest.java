package com.homework.week12.atm_new_with_DB_in_csv_files.repository;

import com.homework.exception.DatabaseException;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;
import org.junit.*;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class CardRepositoryTest {
    private static final Path TEST_ROOT_PATH = Paths.get("src", "test", "java", "com", "homework", "week12", "io_test_root");
    private CardRepository sut;
    private Path testCardDb;

    @Before
    public void createTestingFiles() throws IOException {
        testCardDb = Files.createFile(TEST_ROOT_PATH.resolve("cardDB.csv"));
        createCardDB();
        sut = new CardRepository(testCardDb);
    }

    public void createCardDB() throws IOException {
        String db = "cardID,cardPin\n" +
                "1,1234\n";
        Files.write(testCardDb, db.getBytes());
    }

    @After
    public void cleanUp() throws IOException {
        Files.deleteIfExists(testCardDb);
    }

    @Test
    public void given_Known_Card_Id_When_Get_Card_By_Id_Then_Return_Optional_Of_Card() {
        //Given
        String cardId = "1";
        Card card = Card.builder()
                .cardID("1")
                .pin("1234")
                .build();

        //When
        Optional<Card> result = sut.getCardById(cardId);

        //Then
        assertThat(result).isEqualTo(Optional.of(card));
    }

    @Test
    public void given_Unknown_Card_Id_When_Get_Card_By_Id_Then_Return_Empty_Optional() {
        //Given
        String cardId = "2";

        //When
        Optional<Card> result = sut.getCardById(cardId);

        //Then
        assertThat(result).isEmpty();
    }

    @Test
    public void given_Empty_Card_Id_When_Get_Card_By_Id_Then_Return_Empty_Optional() {
        //Given
        String cardId = "";

        //When
        Optional<Card> result = sut.getCardById(cardId);

        //Then
        assertThat(result).isEmpty();
    }

    @Test
    public void given_Null_Card_Id_When_Get_Card_By_Id_Then_Return_Empty_Optional() {
        //Given
        String cardId = null;

        //When
        Optional<Card> result = sut.getCardById(cardId);

        //Then
        assertThat(result).isEmpty();
    }

    @Test
    public void given_Card_When_Update_Then_Update_Map_And_Write_Card_In_File() throws DatabaseException, IOException {
        //Given
        Card card = Card.builder()
                .cardID("1")
                .pin("1000")
                .build();
        //When
        sut.update(card);

        //Then
        assertThat(sut.getCardMap().get("1").getPin()).isEqualTo("1000");
        assertThat(Files.lines(testCardDb)
                .skip(1)
                .map(line -> line.split(","))
                .anyMatch(line -> line[0].equals("1") && line[1].equals("1000")))
                .isTrue();
    }

    @Test(expected = DatabaseException.class)
    public void given_Unknown_Data_Base_When_Update_Then_Throw_Data_Base_Exception() throws IOException, DatabaseException {
        //Given
        CardRepository sutSpy = Mockito.spy(sut);
        Card card = Card.builder()
                .cardID("1")
                .pin("1000")
                .build();
        Mockito.doThrow(new IOException()).when(sutSpy).writeDb();

        //When
        sutSpy.update(card);
    }

    @Test(expected = UncheckedIOException.class)
    public void given_Unknown_Path_When_Populate_Db_Then_Throw_Exception() {
        //Given
        Path unknown = TEST_ROOT_PATH.resolve("x");
        CardRepository newCardRep = new CardRepository(unknown);

        //When
        newCardRep.populateDB();
    }

    @Test
    public void given_Existing_Card_When_Card_Exists_Then_Return_True() {
        //Given
        Card card = Card.builder()
                .cardID("1")
                .pin("1234")
                .build();
        //When
        boolean result = sut.cardExists(card);

        //Then
        assertThat(result).isTrue();
    }

    @Test
    public void given_Non_Existing_Card_When_Card_Exists_Then_Return_False() {
        //Given
        Card card = Card.builder()
                .cardID("2")
                .pin("1234")
                .build();
        //When
        boolean result = sut.cardExists(card);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    public void given_Null_When_Card_Exists_Then_Return_False() {
        //When
        boolean result = sut.cardExists(null);

        //Then
        assertThat(result).isFalse();
    }
}