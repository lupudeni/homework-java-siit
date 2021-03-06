package com.homework.week10.electronicvote.service;

import com.homework.util.ActionStatus;
import com.homework.week10.electronicvote.entity.Citizen;
import com.homework.exception.EntityNotFoundException;

import java.util.Map;

/**
 * The names have been randomly generated by: https://www.behindthename.com
 * CNP and ID number generated by random.org; CNP- 13 characters, id number- 6 characters
 **/
public class ElectronicVoteService {

    private final CitizenService citizenService;
    private final CandidateService candidateService;

    public ElectronicVoteService() {
        citizenService = new CitizenService();
        candidateService = new CandidateService();
    }

    public ElectronicVoteService(CitizenService citizenService, CandidateService candidateService) {
        this.citizenService = citizenService;
        this.candidateService = candidateService;
    }

    public String vote(String citizenCNP, String candidateName) {
        Citizen citizen;
        int numberOfVotes;
        try {
            citizen = citizenService.getCitizenByCNP(citizenCNP);
            if (citizen.isVoted()) {
                return ActionStatus.FAIL + " : No more than one vote per person!";
            }
            numberOfVotes = candidateService.voteCandidate(candidateName);
            citizen.setVoted(true);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return ActionStatus.FAIL + " : " + e.getMessage();
        }
        return ActionStatus.SUCCESS + "Current votes for candidate: " + numberOfVotes;
    }

    public Map<String, Integer> getPoll() {
        return candidateService.getPoll();
    }
}
