package com.homework.week10.cinema_reservation;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

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
    public void given_Invalid_Name_And_Unreserved_Seat_Number_When_Reserve_Seat_At_Cinema_Then_Return_Reservation_Failed() {
        //Given
        Cinema cinema = sut.addCinema("IMAX", new BigDecimal("30"));
        Seat seat = cinema.getSeat("A1");

        //When
        String reservationStatus = sut.reserveSeatAtCinema("CinePlex", "A1");

        //Then
        assertThat(reservationStatus).isEqualTo(CinemaReservationService.RESERVATION_FAILED);
        assertThat(seat.isReserved()).isEqualTo(false);
    }

    @Test
    public void given_Valid_Name_And_Invalid_Seat_Number_When_Reserve_Seat_At_Cinema_Then_Return_Reservation_Failed() {
        //Given
        Cinema cinema = sut.addCinema("IMAX", new BigDecimal("30"));
        Seat reservedSeat = cinema.getSeat("A1");


        //When
        String reservationStatus = sut.reserveSeatAtCinema("IMAX", "A11");

        //Then
        assertThat(reservationStatus).isEqualTo(CinemaReservationService.RESERVATION_FAILED);

    }

    //deleteReservation

    @Test
    public void given_Valid_Name_And_Reserved_Seat_Number_When_Delete_Reservation_Then_Return_Reservation_Deleted() {
        //Given
        Cinema cinema = sut.addCinema("IMAX", new BigDecimal("30"));
        Seat seat = cinema.getSeat("A1");
        seat.setReserved(true);

        //When
        String reservationStatus = sut.deleteReservation("IMAX", "A1");

        //Then
        assertThat(reservationStatus).isEqualTo(CinemaReservationService.RESERVATION_DELETED);
        assertThat(seat.isReserved()).isEqualTo(false);
    }

    @Test
    public void given_Valid_Name_And_Unreserved_Seat_Number_When_Delete_Reservation_Then_Return_No_Reservation() {
        //Given
        Cinema cinema = sut.addCinema("IMAX", new BigDecimal("30"));
        Seat seat = cinema.getSeat("A1");
        seat.setReserved(false);

        //When
        String reservationStatus = sut.deleteReservation("IMAX", "A1");

        //Then
        assertThat(reservationStatus).isEqualTo(CinemaReservationService.NO_RESERVATION);
        assertThat(seat.isReserved()).isEqualTo(false);
    }

    @Test
    public void given_Invalid_Name_And_Seat_Number_When_Delete_Reservation_Then_Return_No_Reservation() {
        //Given
        Cinema cinema = sut.addCinema("IMAX", new BigDecimal("30"));
        Seat seat = cinema.getSeat("A1");
        seat.setReserved(true);

        //When
        String reservationStatus = sut.deleteReservation("CinePlex", "A1");

        //Then
        assertThat(reservationStatus).isEqualTo(CinemaReservationService.NO_RESERVATION);
        assertThat(seat.isReserved()).isEqualTo(true);
    }

    //removeCinema

    @Test
    public void given_Valid_Cinema_Name_When_Remove_Cinema_Then_Return_Cinema_And_Map_Does_Not_Contain_Cinema() {
        //Given
        Cinema cinema = sut.addCinema("IMAX", new BigDecimal("30"));


        //When
        Cinema result = sut.removeCinema("IMAX");

        //Then
        assertThat(result).isEqualTo(cinema);
        Map<String, Cinema> cinemas = sut.getCinemas();
        assertThat(cinemas).isEmpty();
    }

    @Test
    public void given_Invalid_Cinema_Name_When_Remove_Cinema_Then_Return_Null_And_Map_Is_Unmodified() {
        //Given
        Cinema cinema = sut.addCinema("IMAX", new BigDecimal("30"));

        //When
        Cinema result = sut.removeCinema("CinePlex");

        //Then
        assertThat(result).isEqualTo(null);
        Map<String, Cinema> cinemas = sut.getCinemas();
        assertThat(cinemas).containsValue(cinema);
    }

    //getAvailableSeats

    @Test
    public void given_Valid_Cinema_Name_And_No_Reserved_Seats_When_Get_Available_Seats_Then_Return_Set_Of_100_Seats() {
        //Given
        Cinema cinema = sut.addCinema("IMAX", new BigDecimal("30"));

        //When
        Set<Seat> availableSeats = sut.getAvailableSeats("IMAX");

        //Then
        assertThat(availableSeats.size()).isEqualTo(100);
    }

    @Test
    public void given_Valid_Cinema_Name_And_One_Reserved_Seats_When_Get_Available_Seats_Then_Return_Set_Of_99_Seats() {
        //Given
        Cinema cinema = sut.addCinema("IMAX", new BigDecimal("30"));
        Seat seat = cinema.getSeat("A1");
        seat.setReserved(true);

        //When
        Set<Seat> availableSeats = sut.getAvailableSeats("IMAX");

        //Then
        assertThat(availableSeats.size()).isEqualTo(99);
    }

    @Test
    public void given_Valid_Cinema_Name_And_No_Unreserved_Seats_When_Get_Available_Seats_Then_Return_Empty_Set() {
        //Given
        Cinema cinema = sut.addCinema("IMAX", new BigDecimal("30"));

        for (char ch = 'A'; ch <= 'J'; ch++) {
            for (int i = 1; i <= 10; i++) {
                String seatNr = "" + ch + i;
                cinema.getSeat(seatNr).setReserved(true);
            }
        }

        //When
        Set<Seat> availableSeats = sut.getAvailableSeats("IMAX");

        //Then
        assertThat(availableSeats).isEmpty();
    }

    @Test
    public void given_Invalid_Cinema_Name_When_Get_Available_Seats_Then_Return_Empty_Set() {
        //Given
        Cinema cinema = sut.addCinema("IMAX", new BigDecimal("30"));

        //When
        Set<Seat> availableSeats = sut.getAvailableSeats("CinePlex");

        //Then
        assertThat(availableSeats).isEmpty();
    }

}
