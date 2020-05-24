package com.homework.week12.atm_new_with_DB_in_csv_files.repository;

import com.homework.week12.atm_new_with_DB_in_csv_files.domain.BankAccount;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class BankAccountRepository {

    private final Path bankAccountDb;

    private Map<String, BankAccount> cardIdToAccountMap;
    private Map<String, List<BankAccount>> userIdToAccounts;

    public BankAccountRepository(Map<String, Card> cardMap) {
        this(cardMap, Paths.get("atm_db", "bankAccountDB.csv"));
    }

    public BankAccountRepository(Map<String, Card> cardMap, Path bankAccountDb) {
        this.bankAccountDb = bankAccountDb;
        populateDB(cardMap);
    }

    private void populateDB(Map<String, Card> cardMap) {
        userIdToAccounts = new HashMap<>();
        try {
            cardIdToAccountMap = Files.lines(bankAccountDb)
                    .map(line -> line.split(","))
                    .skip(1)
                    .map(line -> {
                        BankAccount account = BankAccount.builder()
                                .accountID(line[0])
                                .balance(new BigDecimal(line[1]))
                                .card(cardMap.get(line[2]))
                                .build();

                        if(!userIdToAccounts.containsKey(line[3])) {
                            List<BankAccount> accounts = new ArrayList<>();
                            accounts.add(account);
                            userIdToAccounts.put(line[3], accounts);
                        } else {
                            userIdToAccounts.get(line[3]).add(account);
                        }
                        return account;
                    })
                    .collect(Collectors.toMap(account -> account.getCard().getCardID(), account -> account));
        } catch (IOException e) {
            throw new UncheckedIOException("Could not access " + bankAccountDb.toAbsolutePath().normalize().toString(), e);
        }


    }

    public Optional<BankAccount> getBankAccountByCardId(String cardId) {
        return Optional.ofNullable(cardIdToAccountMap.get(cardId));
    }

    public void update(BankAccount account) {
        cardIdToAccountMap.put(account.getCard().getCardID(), account);

    }
}
