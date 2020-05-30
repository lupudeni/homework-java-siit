package com.homework.week12.atm_new_with_DB_in_csv_files.repository;

import com.homework.exception.DatabaseException;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CardRepository {
    private static final String TABLE_HEADER = "cardID,cardPin\n";
    private final Path cardDb;
    private Map<String, Card> cardMap;

    public CardRepository() {
        this(Paths.get("atm_db", "cardDB.csv"));
    }

    public CardRepository(Path cardDb) {
        this.cardDb = cardDb;
        populateDB();
    }

    void populateDB() {
        try {
            cardMap = Files.lines(cardDb)
                    .map(line -> line.split(","))
                    .skip(1)
                    .map(line -> Card.builder()
                            .cardID(line[0])
                            .pin(line[1])
                            .build())
                    .collect(Collectors.toMap(Card::getCardID, card -> card));
        } catch (IOException e) {
            throw new UncheckedIOException("Could not access " + cardDb.toAbsolutePath().normalize().toString(), e);
        }
    }

    public Map<String, Card> getCardMap() {
        return new HashMap<>(cardMap);
    }

    public Optional<Card> getCardById(String cardId) {
        return Optional.ofNullable(cardMap.get(cardId));
    }

    public void update(Card card) throws DatabaseException {
        cardMap.put(card.getCardID(), card);
        try {
            writeDb();
        } catch (IOException e) {
            throw new DatabaseException(cardDb.toAbsolutePath().normalize().toString() + " : Update failure");
        }
    }

    void writeDb() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(cardDb)))) {
            writer.write(TABLE_HEADER);
            for (Card card : cardMap.values()) {
                writer.write(card.getCardID() + "," + card.getPin() + "\n");
            }
        }
    }

    public boolean cardExists(Card card) {
        return cardMap.containsValue(card);
    }
}
