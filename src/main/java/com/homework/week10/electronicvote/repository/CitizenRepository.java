package com.homework.week10.electronicvote.repository;

import com.homework.week10.electronicvote.entity.Citizen;
import com.homework.week10.electronicvote.entity.IDCard;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class CitizenRepository {
    private static final String CITIZENS_TXT = "src/main/java/com/homework/week10/electronicvote/repository/citizens.txt";

    private Map<String, Citizen> citizenDataBase;

    public CitizenRepository() {
        try {
            populateCitizenDataBase();
        } catch (IOException e) {
            throw new UncheckedIOException("Could not access citizens.txt", e);
        }
    }

    private void populateCitizenDataBase() throws IOException {
        Path citizenPath = Paths.get(CITIZENS_TXT);

        citizenDataBase = Files.lines(citizenPath)
                .map(line -> line.split("_"))
                .map(words -> Citizen.builder()
                        .name(words[0])
                        .surname(words[1])
                        .address(words[2])
                        .personalID(IDCard.builder()
                                .cnp(words[3])
                                .idSerial(words[4])
                                .idNumber(words[5])
                                .build())
                        .build())
                .collect(Collectors.toMap(Citizen::getCNP, person -> person));
    }

    public Citizen getCitizenByCNP(String cnp) {
        return citizenDataBase.get(cnp);
    }

}
