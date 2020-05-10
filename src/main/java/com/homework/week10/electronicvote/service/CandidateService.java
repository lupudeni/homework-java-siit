package com.homework.week10.electronicvote.service;

import com.homework.exception.EntityNotFoundException;
import com.homework.week10.electronicvote.repository.CandidateRepository;

import java.util.Map;

public class CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateService() {
        candidateRepository = new CandidateRepository();
    }

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Integer voteCandidate(String candidateName) throws EntityNotFoundException {
        if (candidateName == null) {
            throw new IllegalArgumentException("Candidate name must not be null.");
        }
        if (!candidateRepository.exists(candidateName)) {
            throw new EntityNotFoundException("No candidate with name " + candidateName + " found.");
        }
        return candidateRepository.incrementVotes(candidateName);
    }

    public Map<String, Integer> getPoll() {
        return candidateRepository.getPoll();
    }
}
