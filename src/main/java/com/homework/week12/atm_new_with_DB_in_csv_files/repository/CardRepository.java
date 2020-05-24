package com.homework.week12.atm_new_with_DB_in_csv_files.repository;

import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CardRepository {
    private static final Path CARD_DB = Paths.get("atm_db", "cardDB.csv");

    private Map<String, Card> cardMap;

    public CardRepository() {
        try {
            populateDB();
        } catch (IOException e) {
            throw new UncheckedIOException("Could not access " + CARD_DB.toAbsolutePath().normalize().toString(), e);
        }
    }

    private void populateDB() throws IOException {
        cardMap = Files.lines(CARD_DB)
                 .map(line -> line.split(","))
                 .skip(1)
                 .map(line -> Card.builder()
                         .cardID(line[0])
                         .pin(line[1])
                         .build())
                 .collect(Collectors.toMap(Card::getCardID, card -> card));
    }

    public Map<String, Card> getCardMap() {
        return new HashMap<>(cardMap);
    }
}
