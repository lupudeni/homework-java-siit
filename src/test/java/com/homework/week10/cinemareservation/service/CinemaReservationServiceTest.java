package com.homework.week10.cinemareservation.service;

import com.homework.exception.EntityNotFoundException;
import com.homework.util.ActionStatus;
import com.homework.week10.cinemareservation.entity.Cinema;
import com.homework.week10.cinemareservation.entity.Seat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class CinemaReservationServiceTest {

    private CinemaReservationService sut;
    @Mock
    private CinemaService cinemaService;

    @Before
    public void setUp() {
        sut = new CinemaReservationService(cinemaService);
    }

    @Test
    public void given_Existent_Cinema_And_Unreserved_Seat_When_Reserve_Seat_At_Cinema_Then_Seat_Is_Reserved_And_Return_Reservation_Complete() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");

        Seat seat = new Seat(seatNumber, price);

        Mockito.when(cinemaService.getSeatByCinema(cinemaName, seatNumber)).thenReturn(seat);

        //When
        String status = sut.reserveSeatAtCinema(cinemaName, seatNumber);

        //Then
        assertThat(status).isEqualTo(CinemaReservationService.RESERVATION_COMPLETE);
        assertThat(seat.isReserved()).isTrue();
    }

    @Test
    public void given_Existent_Cinema_And_Reserved_Seat_When_Reserve_Seat_At_Cinema_Then_Seat_Is_Still_Reserved_And_Return_Reservation_Failed() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");

        Seat seat = new Seat(seatNumber, price);
        seat.setReserved(true);
        Mockito.when(cinemaService.getSeatByCinema(cinemaName, seatNumber)).thenReturn(seat);


        //When
        String status = sut.reserveSeatAtCinema(cinemaName, seatNumber);

        //Then
        assertThat(status).isEqualTo(CinemaReservationService.RESERVATION_FAILED);
        assertThat(seat.isReserved()).isTrue();
    }

    @Test
    public void given_Non_Existent_Cinema_And_Seat_When_Reserve_Seat_At_Cinema_Then_Return_Reservation_Failed_And_Exception_Message() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");
        String nonExistentCinema = "Arta";

        Mockito.when(cinemaService.getSeatByCinema(nonExistentCinema, seatNumber)).thenThrow(new EntityNotFoundException("Cinema Arta not found"));

        //When
        String status = sut.reserveSeatAtCinema(nonExistentCinema, seatNumber);

        //Then
        assertThat(status).isEqualTo("Reservation failed : Cinema Arta not found");
    }

    @Test
    public void given_Null_Cinema_And_Seat_When_Reserve_Seat_At_Cinema_Then_Return_Reservation_Failed_And_Exception_Message() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");
        String nullCinema = null;

        Mockito.when(cinemaService.getSeatByCinema(nullCinema, seatNumber)).thenThrow(new IllegalArgumentException("Fields must not be null"));

        //When
        String status = sut.reserveSeatAtCinema(nullCinema, seatNumber);

        //Then
        assertThat(status).isEqualTo("Reservation failed : Fields must not be null");
    }

    @Test
    public void given_Existent_Cinema_And_Reserved_Seat_When_Delete_Reservation_Then_Seat_Is_Unreserved_And_Return_Reservation_Deleted() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");
        Seat seat = new Seat(seatNumber, price);
        seat.setReserved(true);

        Mockito.when(cinemaService.getSeatByCinema(cinemaName, seatNumber)).thenReturn(seat);

        //When
        String status = sut.deleteReservation(cinemaName, seatNumber);

        //Then
        assertThat(status).isEqualTo(CinemaReservationService.RESERVATION_DELETED);
        assertThat(seat.isReserved()).isFalse();
    }

    @Test
    public void given_Existent_Cinema_And_Unreserved_Seat_When_Delete_Reservation_Then_Seat_Is_Still_Unreserved_And_Return_No_Reservation() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");
        Seat seat = new Seat(seatNumber, price);

        Mockito.when(cinemaService.getSeatByCinema(cinemaName, seatNumber)).thenReturn(seat);


        //When
        String status = sut.deleteReservation(cinemaName, seatNumber);

        //Then
        assertThat(status).isEqualTo(CinemaReservationService.NO_RESERVATION);
        assertThat(seat.isReserved()).isFalse();
    }

    @Test
    public void given_Non_Existent_Cinema_And_Seat_When_Delete_Reservation_Then_Return_No_Reservation_And_Exception_Message() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");
        String nonExistentCinema = "Arta";

       Mockito.when(cinemaService.getSeatByCinema(nonExistentCinema, seatNumber)).thenThrow(new EntityNotFoundException("Cinema Arta not found"));

        //When
        String status = sut.deleteReservation(nonExistentCinema, seatNumber);

        //Then
        assertThat(status).isEqualTo("No reservation found. : Cinema Arta not found");
    }

    @Test
    public void given_Null_Cinema_And_Seat_When_Delete_Reservation_Then_Return_No_Reservation_And_Exception_Message() throws EntityNotFoundException {
        //Given
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");
        String nullCinema = null;

        Mockito.when(cinemaService.getSeatByCinema(nullCinema, seatNumber)).thenThrow(new IllegalArgumentException("Fields must not be null"));

        //When
        String status = sut.deleteReservation(nullCinema, seatNumber);

        //Then
        assertThat(status).isEqualTo("No reservation found. : Fields must not be null");
    }

    @Test
    public void given_Valid_Cinema_When_Get_Available_Seats_Then_Return_Set_Of_Available_Seats_To_String() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        BigDecimal price = new BigDecimal("20");
        String seatNumber = "A1";

        Seat seat = new Seat(seatNumber, price);
        Mockito.when(cinemaService.getAvailableSeats(cinemaName)).thenReturn(Set.of(seat));

        //When
        String result = sut.getAvailableSeats(cinemaName);

        //Then
        assertThat(result).contains("[Seat(seatNumber=A1, price=20, reserved=false)]");
    }

    @Test
    public void given_Not_Valid_Cinema_When_Get_Available_Seats_Then_Return_Fail_And_Exception_Message() throws EntityNotFoundException {
        //Given
        BigDecimal price = new BigDecimal("20");
        String nullCinema = null;

        Mockito.when(cinemaService.getAvailableSeats(nullCinema)).thenThrow(new IllegalArgumentException("Cinema name field must not be null"));

        //When
        String result = sut.getAvailableSeats(nullCinema);

        //Then
        assertThat(result).isEqualTo("Fail : Cinema name field must not be null");
    }
}
