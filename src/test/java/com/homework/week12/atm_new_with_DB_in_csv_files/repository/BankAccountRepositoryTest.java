package com.homework.week12.atm_new_with_DB_in_csv_files.repository;

import com.homework.exception.DatabaseException;
import com.homework.exception.EntityNotFoundException;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.BankAccount;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class BankAccountRepositoryTest {
    private static final Path TEST_ROOT_PATH = Paths.get("src", "test", "java", "com", "homework", "week12", "io_test_root");
    private BankAccountRepository sut;
    private Map<String, Card> cardMap;
    private Path testBankAccountDb;

    @Before
    public void setUp() throws IOException {
        cardMap = Map.of("1", Card.builder()
                .cardID("1")
                .pin("1234")
                .build(),
                "2", Card.builder()
                .cardID("2")
                .pin("8900")
                .build());
        testBankAccountDb = Files.createFile(TEST_ROOT_PATH.resolve("bankAccountDB.csv"));
        createBankAccountDB();
        sut = new BankAccountRepository(cardMap, testBankAccountDb);
    }

    private void createBankAccountDB() throws IOException {
        String db = "accountID,balance,cardID,userID\n" +
                "1,4735,1,1\n" +
                "2,100,2,1\n";
        Files.write(testBankAccountDb, db.getBytes());

    }

    @After
    public void cleanUp() throws IOException {
        Files.deleteIfExists(testBankAccountDb);
    }

    @Test
    public void given_Known_CardId_When_Get_Account_By_CardId_Then_Return_Optional_Of_Account() {
        //Given
        Card card = Card.builder()
                .cardID("1")
                .pin("1234")
                .build();
        BankAccount account = BankAccount.builder()
                .accountID("1")
                .balance(new BigDecimal("4735"))
                .card(card)
                .build();
        //When
        Optional<BankAccount> result = sut.getBankAccountByCardId("1");

        //Then
        assertThat(result).isEqualTo(Optional.of(account));
    }

    @Test
    public void given_Unknown_CardId_When_Get_Account_By_CardId_Then_Return_Empty_Optional() {
        //When
        Optional<BankAccount> result = sut.getBankAccountByCardId("3");

        //Then
        assertThat(result).isEmpty();
    }

    @Test
    public void given_Empty_CardId_When_Get_Account_By_CardId_Then_Return_Empty_Optional() {
        //When
        Optional<BankAccount> result = sut.getBankAccountByCardId("");

        //Then
        assertThat(result).isEmpty();
    }

    @Test
    public void given_Null_CardId_When_Get_Account_By_CardId_Then_Return_Empty_Optional() {
        //When
        Optional<BankAccount> result = sut.getBankAccountByCardId(null);

        //Then
        assertThat(result).isEmpty();
    }

    @Test
    public void given_Bank_Account_When_Update_Then_Update_Map_And_Write_CAccount_In_File() throws DatabaseException, EntityNotFoundException, IOException {
        //Given
        Card card = Card.builder()
                .cardID("1")
                .pin("1234")
                .build();
        BankAccount account = BankAccount.builder()
                .accountID("1")
                .balance(new BigDecimal("6000"))
                .card(card)
                .build();

        //When
        sut.update(account);

        //Then
        assertThat(sut.getCardIdToAccountMap().get("1").equals(account));

        assertThat(Files.lines(testBankAccountDb)
                .skip(1)
                .map(line -> line.split(","))
                .anyMatch(line -> line[0].equals("1")
                        && line[1].equals("6000")
                        && line[2].equals("1")
                        && line[3].equals("1")))
                .isTrue();
    }

    @Test(expected = DatabaseException.class)
    public void given_Unknown_Data_Base_When_Update_Then_Throw_Data_Base_Exception() throws IOException, DatabaseException, EntityNotFoundException {
        //Given
        BankAccountRepository spyRep = Mockito.spy(sut);
        Card card = Card.builder()
                .cardID("1")
                .pin("1234")
                .build();
        BankAccount account = BankAccount.builder()
                .accountID("1")
                .balance(new BigDecimal("6000"))
                .card(card)
                .build();
        Mockito.doThrow(new IOException()).when(spyRep).writeDb();

        //When
        spyRep.update(account);
    }

    @Test(expected = UncheckedIOException.class)
    public void given_Unknown_Path_When_Populate_Db_Then_Throw_Exception() {
        //Given
        Path unknown = TEST_ROOT_PATH.resolve("x");
        BankAccountRepository newRep = new BankAccountRepository(cardMap,unknown);

        //When
        newRep.populateDB(cardMap);
    }

//
//    @Test
//    public void getUserIdByAccount() {
//    }
}