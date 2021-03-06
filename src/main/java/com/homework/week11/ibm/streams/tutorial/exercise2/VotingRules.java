package com.homework.week11.ibm.streams.tutorial.exercise2;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing voting rules in an election
 */
public class VotingRules {

    /**
     * Complete this for Exercise_2_Test#getAllPersonsEligibleToVote
     *
     * @param potentialVoters  - voters to filter
     * @param legalAgeOfVoting - age where it's legal to vote
     * @return a list of eligible voters
     */
    public static List<Person> eligibleVoters(List<Person> potentialVoters, int legalAgeOfVoting) {
        // [your code here]
       return potentialVoters.stream()
                .filter(person -> person.getAge() >= legalAgeOfVoting)
                .collect(Collectors.toList());
    }
}
