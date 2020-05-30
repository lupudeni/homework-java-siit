package com.homework.week12.atm_new_with_DB_in_csv_files.repository;

import com.homework.week12.atm_new_with_DB_in_csv_files.domain.BankAccount;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class UserRepositoryTest {
    private static final Path TEST_ROOT_PATH = Paths.get("src", "test", "java", "com", "homework", "week12", "io_test_root");
    private Map<String, List<BankAccount>> userIdToAccounts;
    private Path testUserDb;
    private UserRepository sut;
    private List<BankAccount> accounts;

    @Before
    public void setUp() throws IOException {
        accounts = createAccountsList();
        userIdToAccounts = Map.of("1", accounts);
        testUserDb = Files.createFile(TEST_ROOT_PATH.resolve("userDB.csv"));
        createUserDb();

        sut = new UserRepository(userIdToAccounts, testUserDb);
    }

    private void createUserDb() throws IOException {
        String db = "userID,userName\n" +
                "1,Zoe Chandler\n";
        Files.write(testUserDb, db.getBytes());

    }

    private List<BankAccount> createAccountsList() {
        Card card1 = Card.builder()
                .cardID("1")
                .pin("1234")
                .build();
        BankAccount account1 = BankAccount.builder()
                .accountID("1")
                .balance(new BigDecimal("4735"))
                .card(card1)
                .build();

        Card card2 = Card.builder()
                .cardID("2")
                .pin("8900")
                .build();
        BankAccount account2 = BankAccount.builder()
                .accountID("2")
                .balance(new BigDecimal("100"))
                .card(card2)
                .build();
        return List.of(account1, account2);
    }

    @After
    public void cleanUp() throws IOException {
        Files.deleteIfExists(testUserDb);
    }

    @Test
    public void given_Id_When_Get_User_By_Id_Then_Return_Optional_Of_User() {
        //Given
        User user = User.builder()
                .userId("1")
                .name("Zoe Chandler")
                .bankAccount(accounts)
                .build();

        //When
        Optional<User> result = sut.getUserById("1");

        //Then
        assertThat(result).isEqualTo(Optional.of(user));
    }

    @Test
    public void given_Unknown_Id_When_Get_User_By_Id_Then_Return_Empty_Optional() {
        //When
        Optional<User> result = sut.getUserById("2");

        //Then
        assertThat(result).isEmpty();
    }

    @Test
    public void given_Empty_Id_When_Get_User_By_Id_Then_Return_Empty_Optional() {
        //When
        Optional<User> result = sut.getUserById("");

        //Then
        assertThat(result).isEmpty();
    }

    @Test
    public void given_Null_When_Get_User_By_Id_Then_Return_Empty_Optional() {
        //When
        Optional<User> result = sut.getUserById(null);

        //Then
        assertThat(result).isEmpty();
    }

    @Test(expected = UncheckedIOException.class)
    public void given_Unknown_Path_When_PopulateDb_Then_Throw_Exception() {
        //Given
        Path unknown = TEST_ROOT_PATH.resolve("x");
        UserRepository newRep = new UserRepository(userIdToAccounts,unknown);

        //When
        newRep.populateDb(userIdToAccounts);

    }

}