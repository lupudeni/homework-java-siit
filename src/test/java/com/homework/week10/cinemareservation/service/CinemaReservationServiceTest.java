package com.homework.week10.cinemareservation.service;

import com.homework.util.ActionStatus;
import com.homework.week10.cinemareservation.entity.Cinema;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class CinemaReservationServiceTest {

    CinemaReservationService sut;
    @Mock
    CinemaService cinemaService = new CinemaService();

    @Before
    public void setUp() {
        sut = new CinemaReservationService(cinemaService);
    }

    @Test
    public void given_Existent_Cinema_And_Unreserved_Seat_When_Reserve_Seat_At_Cinema_Then_Seat_Is_Reserved_And_Return_Reservation_Complete() {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");

        Cinema cinema = cinemaService.addCinema(cinemaName, price);

        //When
        String status = sut.reserveSeatAtCinema(cinemaName, seatNumber);

        //Then
        assertThat(status).isEqualTo(CinemaReservationService.RESERVATION_COMPLETE);
        assertThat(cinema.getSeat(seatNumber).isReserved()).isEqualTo(true);
    }

    @Test
    public void given_Existent_Cinema_And_Reserved_Seat_When_Reserve_Seat_At_Cinema_Then_Seat_Is_Still_Reserved_And_Return_Reservation_Failed() {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");

        Cinema cinema = cinemaService.addCinema(cinemaName, price);
        cinema.getSeat("A1").setReserved(true);

        //When
        String status = sut.reserveSeatAtCinema(cinemaName, seatNumber);

        //Then
        assertThat(status).isEqualTo(CinemaReservationService.RESERVATION_FAILED);
        assertThat(cinema.getSeat(seatNumber).isReserved()).isEqualTo(true);
    }

    @Test
    public void given_Non_Existent_Cinema_And_Seat_When_Reserve_Seat_At_Cinema_Then_Return_Reservation_Failed_And_Exception_Message() {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");
        String nonExistentCinema = "Arta";

        Cinema cinema = cinemaService.addCinema(cinemaName, price);

        //When
        String status = sut.reserveSeatAtCinema(nonExistentCinema, seatNumber);

        //Then
        assertThat(status).contains(CinemaReservationService.RESERVATION_FAILED);
    }

    @Test
    public void given_Null_Cinema_And_Seat_When_Reserve_Seat_At_Cinema_Then_Return_Reservation_Failed_And_Exception_Message() {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");
        String nullCinema = null;

        Cinema cinema = cinemaService.addCinema(cinemaName, price);

        //When
        String status = sut.reserveSeatAtCinema(nullCinema, seatNumber);

        //Then
        assertThat(status).contains(CinemaReservationService.RESERVATION_FAILED);
    }

    @Test
    public void given_Existent_Cinema_And_Reserved_Seat_When_Delete_Reservation_Then_Seat_Is_Unreserved_And_Return_Reservation_Deleted() {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");

        Cinema cinema = cinemaService.addCinema(cinemaName, price);
        cinema.getSeat("A1").setReserved(true);

        //When
        String status = sut.deleteReservation(cinemaName, seatNumber);

        //Then
        assertThat(status).isEqualTo(CinemaReservationService.RESERVATION_DELETED);
        assertThat(cinema.getSeat(seatNumber).isReserved()).isEqualTo(true);
    }

    @Test
    public void given_Existent_Cinema_And_Unreserved_Seat_When_Delete_Reservation_Then_Seat_Is_Still_Unreserved_And_Return_No_Reservation() {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");

        Cinema cinema = cinemaService.addCinema(cinemaName, price);

        //When
        String status = sut.deleteReservation(cinemaName, seatNumber);

        //Then
        assertThat(status).isEqualTo(CinemaReservationService.NO_RESERVATION);
        assertThat(cinema.getSeat(seatNumber).isReserved()).isEqualTo(false);
    }

    @Test
    public void given_Non_Existent_Cinema_AndSeat_When_Delete_Reservation_Then_Return_No_Reservation_And_Exception_Message() {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");
        String nonExistentCinema = "Arta";

        Cinema cinema = cinemaService.addCinema(cinemaName, price);

        //When
        String status = sut.deleteReservation(nonExistentCinema, seatNumber);

        //Then
        assertThat(status).contains(CinemaReservationService.NO_RESERVATION);
    }

    @Test
    public void given_Null_Cinema_AndSeat_When_Delete_Reservation_Then_Return_No_Reservation_And_Exception_Message() {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");
        String nullCinema = null;

        Cinema cinema = cinemaService.addCinema(cinemaName, price);

        //When
        String status = sut.deleteReservation(nullCinema, seatNumber);

        //Then
        assertThat(status).contains(CinemaReservationService.NO_RESERVATION);
    }

    @Test
    public void given_Valid_Cinema_When_Get_Available_Seats_Then_Return_Set_Of_Available_Seats_To_String() {
        //Given
        String cinemaName = "IMAX";
        BigDecimal price = new BigDecimal("20");

        Cinema cinema = cinemaService.addCinema(cinemaName, price);

        //When
        String result = sut.getAvailableSeats(cinemaName);

        //Then
        assertThat(result).contains("[Seat(seatNumber=I8, price=20, reserved=false), Seat(seatNumber=D9, price=20, reserved=false), Seat(seatNumber=F7, price=22.0, reserved=false), Seat(seatNumber=A5, price=20, reserved=false), Seat(seatNumber=D4, price=20, reserved=false), Seat(seatNumber=E10, price=20, reserved=false), Seat(seatNumber=B9, price=20, reserved=false), Seat(seatNumber=G8, price=22.0, reserved=false), Seat(seatNumber=G3, price=22.0, reserved=false), Seat(seatNumber=B4, price=20, reserved=false), Seat(seatNumber=E8, price=20, reserved=false), Seat(seatNumber=E3, price=20, reserved=false), Seat(seatNumber=H2, price=20, reserved=false), Seat(seatNumber=J9, price=20, reserved=false), Seat(seatNumber=C8, price=20, reserved=false), Seat(seatNumber=J4, price=20, reserved=false), Seat(seatNumber=H7, price=20, reserved=false), Seat(seatNumber=F10, price=20, reserved=false), Seat(seatNumber=C3, price=20, reserved=false), Seat(seatNumber=F2, price=20, reserved=false), Seat(seatNumber=I1, price=20, reserved=false), Seat(seatNumber=D2, price=20, reserved=false), Seat(seatNumber=I6, price=20, reserved=false), Seat(seatNumber=D7, price=20, reserved=false), Seat(seatNumber=A8, price=20, reserved=false), Seat(seatNumber=G1, price=20, reserved=false), Seat(seatNumber=A3, price=20, reserved=false), Seat(seatNumber=F5, price=22.0, reserved=false), Seat(seatNumber=E1, price=20, reserved=false), Seat(seatNumber=B7, price=20, reserved=false), Seat(seatNumber=E6, price=20, reserved=false), Seat(seatNumber=G5, price=22.0, reserved=false), Seat(seatNumber=B2, price=20, reserved=false), Seat(seatNumber=H5, price=20, reserved=false), Seat(seatNumber=C1, price=20, reserved=false), Seat(seatNumber=J6, price=20, reserved=false), Seat(seatNumber=D10, price=20, reserved=false), Seat(seatNumber=J1, price=20, reserved=false), Seat(seatNumber=C6, price=20, reserved=false), Seat(seatNumber=A6, price=20, reserved=false), Seat(seatNumber=I4, price=20, reserved=false), Seat(seatNumber=I9, price=20, reserved=false), Seat(seatNumber=D5, price=20, reserved=false), Seat(seatNumber=A1, price=20, reserved=false), Seat(seatNumber=F3, price=22.0, reserved=false), Seat(seatNumber=B5, price=20, reserved=false), Seat(seatNumber=G9, price=20, reserved=false), Seat(seatNumber=F8, price=22.0, reserved=false), Seat(seatNumber=G7, price=22.0, reserved=false), Seat(seatNumber=B10, price=20, reserved=false), Seat(seatNumber=E9, price=20, reserved=false), Seat(seatNumber=C4, price=20, reserved=false), Seat(seatNumber=H8, price=20, reserved=false), Seat(seatNumber=J8, price=20, reserved=false), Seat(seatNumber=E4, price=20, reserved=false), Seat(seatNumber=H3, price=20, reserved=false), Seat(seatNumber=J3, price=20, reserved=false), Seat(seatNumber=A9, price=20, reserved=false), Seat(seatNumber=A4, price=20, reserved=false), Seat(seatNumber=I10, price=20, reserved=false), Seat(seatNumber=I2, price=20, reserved=false), Seat(seatNumber=I7, price=20, reserved=false), Seat(seatNumber=C9, price=20, reserved=false), Seat(seatNumber=G2, price=20, reserved=false), Seat(seatNumber=B3, price=20, reserved=false), Seat(seatNumber=F6, price=22.0, reserved=false), Seat(seatNumber=H10, price=20, reserved=false), Seat(seatNumber=D3, price=20, reserved=false), Seat(seatNumber=D8, price=20, reserved=false), Seat(seatNumber=G4, price=22.0, reserved=false), Seat(seatNumber=E7, price=20, reserved=false), Seat(seatNumber=H1, price=20, reserved=false), Seat(seatNumber=E2, price=20, reserved=false), Seat(seatNumber=J5, price=20, reserved=false), Seat(seatNumber=H6, price=20, reserved=false), Seat(seatNumber=B8, price=20, reserved=false), Seat(seatNumber=A2, price=20, reserved=false), Seat(seatNumber=G10, price=20, reserved=false), Seat(seatNumber=F1, price=20, reserved=false), Seat(seatNumber=C7, price=20, reserved=false), Seat(seatNumber=I5, price=20, reserved=false), Seat(seatNumber=C2, price=20, reserved=false), Seat(seatNumber=J10, price=20, reserved=false), Seat(seatNumber=F4, price=22.0, reserved=false), Seat(seatNumber=D1, price=20, reserved=false), Seat(seatNumber=D6, price=20, reserved=false), Seat(seatNumber=A7, price=20, reserved=false), Seat(seatNumber=E5, price=20, reserved=false), Seat(seatNumber=G6, price=22.0, reserved=false), Seat(seatNumber=A10, price=20, reserved=false), Seat(seatNumber=C10, price=20, reserved=false), Seat(seatNumber=H4, price=20, reserved=false), Seat(seatNumber=H9, price=20, reserved=false), Seat(seatNumber=J7, price=20, reserved=false), Seat(seatNumber=B1, price=20, reserved=false), Seat(seatNumber=B6, price=20, reserved=false), Seat(seatNumber=J2, price=20, reserved=false), Seat(seatNumber=F9, price=20, reserved=false), Seat(seatNumber=I3, price=20, reserved=false), Seat(seatNumber=C5, price=20, reserved=false)]");
    }

    @Test
    public void given_Not_Valid_Cinema_When_Get_Available_Seats_Then_Return_Fail_And_Exception_Message() {
        //Given
        String cinemaName = "IMAX";
        BigDecimal price = new BigDecimal("20");
        String nullCinema = null;

        Cinema cinema = cinemaService.addCinema(cinemaName, price);

        //When
        String result = sut.getAvailableSeats(nullCinema);

        //Then
        assertThat(result).contains(ActionStatus.FAIL);
    }
}
