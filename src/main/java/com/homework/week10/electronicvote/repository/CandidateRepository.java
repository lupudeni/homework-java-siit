package com.homework.week10.electronicvote.repository;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandidateRepository {
    private static final String CANDIDATES_TXT = "src/main/java/com/homework/week10/electronicvote/repository/candidates.txt";

    private Map<String, Integer> candidateDataBase;

    public CandidateRepository() {
        try {
            populateCandidateDataBase();
        } catch (IOException e) {
            throw new UncheckedIOException("Could not access candidates.txt", e);
        }
    }

    private void populateCandidateDataBase() throws IOException {
        Path candidatePath = Paths.get(CANDIDATES_TXT);
        List<String> candidateNames = Files.readAllLines(candidatePath);
        candidateDataBase = new HashMap<>();
        candidateNames.forEach(candidate -> candidateDataBase.put(candidate, 0));
    }

    public boolean exists(String name) {
        return candidateDataBase.containsKey(name);
    }

    public Integer incrementVotes(String candidateName) {
        return candidateDataBase.compute(candidateName, (name, votes) -> votes == null ? 0 : votes + 1);
    }
    public Map<String, Integer> getPoll() {
        return new HashMap<>(candidateDataBase);
    }
}
