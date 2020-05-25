package com.homework.week12.atm_new_with_DB_in_csv_files.repository;

import com.homework.week12.atm_new_with_DB_in_csv_files.domain.BankAccount;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.User;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepository {

    private final Path userDb;
    private Map<User,List <BankAccount>> userToAccount;

    public UserRepository(Map<String, List<BankAccount>> userIdToAccounts) {
        this(userIdToAccounts, Paths.get("atm_db", "userDB.csv"));
    }

    public UserRepository(Map<String, List<BankAccount>> userIdToAccounts, Path userDb) {
        this.userDb = userDb;
        populateDb(userIdToAccounts);
    }

    private void populateDb(Map<String, List<BankAccount>> userIdToAccounts) {
        System.out.println("userIdToAccounts = " + userIdToAccounts.toString());
        try {
            userToAccount = Files.lines(userDb)
                    .map(line -> line.split(","))
                    .skip(1)
                    .map(line -> User.builder()
                            .userId(line[0])
                            .name(line[1])
                            .build())
                    .collect(Collectors.toMap(user -> user, user -> userIdToAccounts.get(user.getUserId())));
        } catch (IOException e) {
            throw new UncheckedIOException("Could not access " + userDb.toAbsolutePath().normalize().toString(), e);

        }
    }

    public Optional<User> getUserById(String userId) {
       return userToAccount.keySet().stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();
    }
}
