package com.homework.week10.electronicvote.service;

import com.homework.exception.EntityNotFoundException;
import com.homework.week10.electronicvote.repository.CandidateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class CandidateServiceTest {
    private CandidateService sut;

    @Mock
    private CandidateRepository candidateRepository;

    @Before
    public void setUp() {
        sut = new CandidateService(candidateRepository);
    }

    @Test
    public void given_Known_Candidate_When_Vote_Candidate_Then_Return_Number_Of_Votes() throws EntityNotFoundException {
        //Given
        String knownName = "Adrian Ardelean";
        Mockito.when(candidateRepository.exists(knownName)).thenReturn(true);
        Mockito.when(candidateRepository.incrementVotes(knownName)).thenReturn(1);

        //When
        int numberOfVotes = sut.voteCandidate(knownName);

        //Then
        assertThat(numberOfVotes).isEqualTo(1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void given_Unknown_Candidate_When_Vote_Candidate_Then_Throw_Exception() throws EntityNotFoundException {
        //Given
        String unknownName = "Abc";
        Mockito.when(candidateRepository.exists(unknownName)).thenReturn(false);

        //When
        sut.voteCandidate(unknownName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_null_Candidate_When_Vote_Candidate_Then_Throw_Exception() throws EntityNotFoundException {
        //Given
        String nullName = null;

        //When
        sut.voteCandidate(nullName);
    }

}
