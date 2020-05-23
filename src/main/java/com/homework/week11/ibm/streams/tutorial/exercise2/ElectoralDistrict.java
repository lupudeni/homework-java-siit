package com.homework.week11.ibm.streams.tutorial.exercise2;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public enum ElectoralDistrict {

    CROYDON("CR"),
    BARKING("BA"),
    HACKNEY("HA"),
    EDMONTON("ED");

    private final String prefix;

    ElectoralDistrict(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Complete this method to pass Exercise_2_Test#setOfVotersInDistrict()
     *
     * @param district - District to vote in
     * @param voters   - voters to filter
     * @return filtered set of registered voters in a district
     */
    public static Set<RegisteredVoter> votersIn(ElectoralDistrict district, Collection<RegisteredVoter> voters) {
        // [your code here]
        return Collections.unmodifiableSet(
                voters.stream()
                        .filter(voter -> voter.getElectorId().startsWith(district.getPrefix()))
                        .collect(Collectors.toSet()));
    }

    /**
     * Complete this method to pass Exercise_2_Test#removeAllSpoiledBallots()
     *
     * @param ballots - ballots to filter
     * @return filtered set of unspoiled ballots
     */
    public static Set<Ballot> unspoiledBallots(Set<Ballot> ballots) {
        // [your code here]
        return ballots.stream()
                .filter(ballot -> !ballot.isSpoiled())
                .collect(Collectors.toSet());
    }

    public String getPrefix() {
        return prefix;
    }
}

