package com.homework.week10.electronicvote.service;

import com.homework.exception.EntityNotFoundException;
import com.homework.week10.electronicvote.entity.Citizen;
import com.homework.week10.electronicvote.entity.IDCard;
import com.homework.week10.electronicvote.repository.CitizenRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class CitizenServiceTest {
    private CitizenService sut;

    @Mock
    private CitizenRepository citizenRepository;

    @Before
    public void setUp() {
        sut = new CitizenService(citizenRepository);
    }

    @Test
    public void given_Known_Citizen_CNP_When_Get_Zitizen_By_Id_Then_Return_Citizen() throws EntityNotFoundException {
        //Given
        String cnp = "2766851172781";
        Citizen citizen = Citizen.builder()
                .name("Sofia")
                .surname("Petrescu")
                .address("Cluj Napoca")
                .personalID(IDCard.builder()
                        .cnp(cnp)
                        .idSerial("CJ")
                        .idNumber("839939")
                        .build())
                .build();
        Mockito.when(citizenRepository.getCitizenByCNP(cnp)).thenReturn(citizen);

        //When
        Citizen result = sut.getCitizenByCNP(cnp);

        //Then
        assertThat(result).isEqualTo(citizen);
    }

    @Test(expected = EntityNotFoundException.class)
    public void given_Unknown_Citizen_CNP_When_Get_Zitizen_By_Id_Then_Throe_Exception() throws EntityNotFoundException {
        //Given
        String cnp = "2766851172781";
        Mockito.when(citizenRepository.getCitizenByCNP(cnp)).thenReturn(null);

        //When
        sut.getCitizenByCNP(cnp);
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Null_Citizen_CNP_When_Get_Zitizen_By_Id_Then_Throe_Exception() throws EntityNotFoundException {
        //Given
        String cnp = null;

        //When
        sut.getCitizenByCNP(cnp);
    }
}
