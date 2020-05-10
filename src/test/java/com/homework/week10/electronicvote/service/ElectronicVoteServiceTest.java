package com.homework.week10.electronicvote.service;

import com.homework.exception.EntityNotFoundException;
import com.homework.util.ActionStatus;
import com.homework.week10.electronicvote.entity.Citizen;
import com.homework.week10.electronicvote.entity.IDCard;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ElectronicVoteServiceTest {
    private ElectronicVoteService sut;

    @Mock
    private CitizenService citizenService;

    @Mock
    private CandidateService candidateService;

    @Before
    public void setUp() {
        sut = new ElectronicVoteService(citizenService, candidateService);
    }

    @Test
    public void given_Known_Citizen_Known_Candidate_And_False_Voting_Status_When_Vote_Then_Return_Success() throws EntityNotFoundException {
        //Given
        String citizenCNP = "2766851172781";
        String candidateName = "Adrian Ardelean";
        Citizen citizen = Citizen.builder()
                .name("Sofia")
                .surname("Petrescu")
                .address("Cluj Napoca")
                .personalID(IDCard.builder()
                        .cnp(citizenCNP)
                        .idSerial("CJ")
                        .idNumber("839939")
                        .build())
                .build();
        Mockito.when(citizenService.getCitizenByCNP(citizenCNP)).thenReturn(citizen);

        //When
        String status = sut.vote(citizenCNP, candidateName);

        //Then
        assertThat(status).contains(ActionStatus.SUCCESS);
    }

    @Test
    public void given_Unknown_Citizen_Known_Candidate_When_Vote_Then_Return_Fail() throws EntityNotFoundException {
        //Given
        String citizenCNP = "2766851172781";
        String candidateName = "Adrian Ardelean";
        Mockito.when(citizenService.getCitizenByCNP(citizenCNP)).thenThrow(EntityNotFoundException.class);

        //When
        String status = sut.vote(citizenCNP, candidateName);

        //Then
        assertThat(status).contains(ActionStatus.FAIL);
    }

    @Test
    public void given_Known_Citizen_Known_Candidate_And_True_Voting_Status_When_Vote_Then_Return_Fail() throws EntityNotFoundException {
        //Given
        String citizenCNP = "2766851172781";
        String candidateName = "Adrian Ardelean";
        Citizen citizen = Citizen.builder()
                .name("Sofia")
                .surname("Petrescu")
                .address("Cluj Napoca")
                .personalID(IDCard.builder()
                        .cnp(citizenCNP)
                        .idSerial("CJ")
                        .idNumber("839939")
                        .build())
                .build();
        citizen.setVoted(true);
        Mockito.when(citizenService.getCitizenByCNP(citizenCNP)).thenReturn(citizen);

        //When
        String status = sut.vote(citizenCNP, candidateName);

        //Then
        assertThat(status).contains(ActionStatus.FAIL);
    }

}
