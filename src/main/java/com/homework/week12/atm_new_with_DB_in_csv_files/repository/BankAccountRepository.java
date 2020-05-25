package com.homework.week12.atm_new_with_DB_in_csv_files.repository;

import com.homework.exception.DatabaseException;
import com.homework.exception.EntityNotFoundException;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.BankAccount;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class BankAccountRepository {

    private static final String TABLE_HEADER = "accountID,balance,cardID,userID\n";
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

                        if (!userIdToAccounts.containsKey(line[3])) {
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

    public Map<String, List<BankAccount>> getUserIdToAccounts() {
        return new HashMap<>(userIdToAccounts);
    }

    public Optional<BankAccount> getBankAccountByCardId(String cardId) {
        return Optional.ofNullable(cardIdToAccountMap.get(cardId));
    }

    public void update(BankAccount account) throws DatabaseException {
        cardIdToAccountMap.put(account.getCard().getCardID(), account);
        try {
            writeDb();
        } catch (IOException | EntityNotFoundException e) {
            throw new DatabaseException(bankAccountDb.toAbsolutePath().normalize().toString() + " : Update failure");
        }

    }

    private void writeDb() throws IOException, EntityNotFoundException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(bankAccountDb)))) {
            writer.write(TABLE_HEADER);

            for (BankAccount account : cardIdToAccountMap.values()) {
               String line = account.getAccountID() + ","
                        + account.getBalance().toString() + ","
                        + account.getCard().getCardID() + ","
                        + getUserIdByAccount(account) +"\n";
               writer.write(line);
            }
        }
    }

    public String getUserIdByAccount(BankAccount account) throws EntityNotFoundException {
       return userIdToAccounts.entrySet()
                .stream()
                .filter(userIdToAccountListEntry -> userIdToAccountListEntry.getValue().contains(account))
                .map(Map.Entry::getKey)
                .findAny()
                .orElseThrow(() -> new EntityNotFoundException("No user linked to bank account id '" + account.getAccountID() + "'"));
    }
}
