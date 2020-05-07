package com.homework.week10.cinema_reservation;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class CinemaReservationServiceTest {

    private CinemaReservationService sut;

    @Before
    public void setUp() {
        sut = new CinemaReservationService();
    }


    //reserveSeatAtCinema
    @Test
    public void given_Valid_Name_And_Unreserved_Seat_Number_When_Reserve_Seat_At_Cinema_Then_Return_Reservation_Complete() {
        //Given
        Cinema cinema = sut.addCinema("IMAX", new BigDecimal("30"));
        Seat seat = cinema.getSeat("A1");

        //When
        String reservationStatus = sut.reserveSeatAtCinema("IMAX", "A1");

        //Then
        assertThat(reservationStatus).isEqualTo(CinemaReservationService.RESERVATION_COMPLETE);
        assertThat(seat.isReserved()).isEqualTo(true);
    }

    @Test
    public void given_Valid_Name_And_Reserved_Seat_Number_When_Reserve_Seat_At_Cinema_Then_Return_Reservation_Failed() {
        //Given
        Cinema cinema = sut.addCinema("IMAX", new BigDecimal("30"));
        Seat reservedSeat = cinema.getSeat("A1");
        reservedSeat.setReserved(true);

        //When
        String reservationStatus = sut.reserveSeatAtCinema("IMAX", "A1");

        //Then
        assertThat(reservationStatus).isEqualTo(CinemaReservationService.RESERVATION_FAILED);
        assertThat(reservedSeat.isReserved()).isEqualTo(true);
    }

    @Test
    public void given_Inalid_Name_And_Unreserved_Seat_Number_When_Reserve_Seat_At_Cinema_Then_Return_Reservation_Failed() {
        //Given
        Cinema cinema = sut.addCinema("IMAX", new BigDecimal("30"));
        Seat seat = cinema.getSeat("A1");

        //When
        String reservationStatus = sut.reserveSeatAtCinema("CinePlex", "A1");

        //Then
        assertThat(reservationStatus).isEqualTo(CinemaReservationService.RESERVATION_FAILED);
        assertThat(seat.isReserved()).isEqualTo(false);
    }
}
