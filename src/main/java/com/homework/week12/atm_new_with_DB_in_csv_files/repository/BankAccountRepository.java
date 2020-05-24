package com.homework.week12.atm_new_with_DB_in_csv_files.repository;

import com.homework.week12.atm_new_with_DB_in_csv_files.domain.BankAccount;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class BankAccountRepository {

    private static final Path BANK_ACCOUNT_DB = Paths.get("atm_db", "bankAccountDB.csv");


    private Map<String, BankAccount> cardIdToAccountMap;

    public BankAccountRepository() {
        try {
            populateDB();
        } catch (IOException e) {
            throw new UncheckedIOException("Could not access " + BANK_ACCOUNT_DB.toAbsolutePath().normalize().toString(), e);

        }

    }

    private void populateDB() throws IOException {
        cardIdToAccountMap = Files.lines(BANK_ACCOUNT_DB)
                .map(line -> line.split(","))
                .skip(1)
                .map(line -> BankAccount.builder()
                        .accountID(line[0])
                        .balance(new BigDecimal(line[1]))
                        .card(Card.builder()
                                .cardID(line[2])
                                .build())
                        .build())
                .collect(Collectors.toMap(BankAccount::getAccountID, account -> account));
    }
}
