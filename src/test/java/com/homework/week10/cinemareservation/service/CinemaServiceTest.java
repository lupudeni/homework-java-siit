package com.homework.week10.cinemareservation.service;

import com.homework.exception.EntityNotFoundException;
import com.homework.week10.cinemareservation.entity.Cinema;
import com.homework.week10.cinemareservation.entity.Seat;
import com.homework.week10.cinemareservation.repository.CinemaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class CinemaServiceTest {
    private CinemaService sut;

    @Mock
    CinemaRepository cinemaRepository;

    @Before
    public void setUp() {
        sut = new CinemaService(cinemaRepository);
    }

    @Test
    public void given_Basic_Seat_And_Price_When_Generate_Price_Then_Return_Basic_Seat_Price() {
        //Given
        String seatNumber = "A1";
        BigDecimal cinemaPrice = new BigDecimal("20");

        //When
        BigDecimal seatPrice = sut.generatePrice(seatNumber, cinemaPrice);

        //Then
        assertThat(seatPrice).isEqualTo("20");
    }

    @Test
    public void given_Premium_Seat_And_Price_When_Generate_Price_Then_Return_Enhanced_Seat_Price() {
        //Given
        String seatNumber = "F3";
        BigDecimal cinemaPrice = new BigDecimal("20");

        //When
        BigDecimal seatPrice = sut.generatePrice(seatNumber, cinemaPrice);

        //Then
        BigDecimal enhancedPrice = cinemaPrice.multiply(new BigDecimal("1.1"));
        assertThat(seatPrice).isEqualTo(enhancedPrice);
    }

    @Test
    public void given_Not_Null_Name_And_Price_When_Add_Cinema_Then_Add_Cinema_To_Repository_And_Return_Cinema() {
        //Given
        String name = "IMAX";
        BigDecimal price = new BigDecimal("20");

        //When
        Cinema cinema = sut.addCinema(name, price);

        //Then
        assertThat(cinemaRepository.getCinemaByName("IMAX")).isEqualTo(cinema);
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Null_Name_And_Price_When_Add_Cinema_Then_Throw_Exception() {
        //Given
        String name = null;
        BigDecimal price = new BigDecimal("20");

        //When
        Cinema cinema = sut.addCinema(name, price);
    }

    @Test
    public void given_Existent_Cinema_Name_And_Seat_When_Get_Seat_By_Cinema_Then_Return_Seat() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "A1";
        BigDecimal price = new BigDecimal("20");

        sut.addCinema(cinemaName, price);

        //When
        Seat seat = sut.getSeatByCinema(cinemaName, seatNumber);

        //Then
        assertThat(seat.getSeatNumber()).isEqualTo(seatNumber);
    }

    @Test(expected = EntityNotFoundException.class)
    public void given_Existent_Cinema_Name_And_Non_Existent_Seat_When_Get_Seat_By_Cinema_Then_Throw_Exception() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        String seatNumber = "Z1";
        BigDecimal price = new BigDecimal("20");

        sut.addCinema(cinemaName, price);

        //When
        Seat seat = sut.getSeatByCinema(cinemaName, seatNumber);
    }

    @Test(expected = EntityNotFoundException.class)
    public void given_Non_Existent_Cinema_Name_And_Seat_When_Get_Seat_By_Cinema_Then_Throw_Exception() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        String nonExistingCinema = "Arta";
        String seatNumber = "Z1";
        BigDecimal price = new BigDecimal("20");

        sut.addCinema(cinemaName, price);

        //When
        Seat seat = sut.getSeatByCinema(nonExistingCinema, seatNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Null_Cinema_Name_And_Seat_When_Get_Seat_By_Cinema_Then_Throw_Exception() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        String nullCinema = null;
        String seatNumber = "Z1";
        BigDecimal price = new BigDecimal("20");

        sut.addCinema(cinemaName, price);

        //When
        Seat seat = sut.getSeatByCinema(nullCinema, seatNumber);
    }

    @Test
    public void given_Existent_Cinema_Name_And_No_Reserved_Seats_When_Get_Available_Seats_Then_Return_Set_Of_Size_100() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        BigDecimal price = new BigDecimal("20");

        sut.addCinema(cinemaName, price);

        //When
        int size = sut.getAvailableSeats(cinemaName).size();

        //Then
        assertThat(size).isEqualTo(100);
    }

    @Test
    public void given_Existent_Cinema_Name_And_One_Reserved_Seat_When_Get_Available_Seats_Then_Return_Set_Of_Size_99() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        BigDecimal price = new BigDecimal("20");

        Cinema cinema = sut.addCinema(cinemaName, price);
        cinema.getSeat("A1").setReserved(true);

        //When
        int size = sut.getAvailableSeats(cinemaName).size();

        //Then
        assertThat(size).isEqualTo(99);
    }

    @Test
    public void given_Existent_Cinema_Name_And_No_Unreserved_Seats_When_Get_Available_Seats_Then_Return_Empty_Set() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        BigDecimal price = new BigDecimal("20");

        Cinema cinema = sut.addCinema(cinemaName, price);

        for (char ch = 'A'; ch <= 'J'; ch++) {
            for (int i = 1; i <= 10; i++) {
                String seatNr = "" + ch + i;
                cinema.getSeat(seatNr).setReserved(true);
            }
        }
        //When
        Set<Seat> seats = sut.getAvailableSeats(cinemaName);

        //Then
        assertThat(seats).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_Null_Cinema_Name_When_Get_Available_Seats_Then_Throw_Exception() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        BigDecimal price = new BigDecimal("20");
        String nullCinema = null;

        Cinema cinema = sut.addCinema(cinemaName, price);

        //When
        Set<Seat> seats = sut.getAvailableSeats(nullCinema);
    }

    @Test(expected = EntityNotFoundException.class)
    public void given_Non_Existent_Cinema_Name_When_Get_Available_Seats_Then_Throw_Exception() throws EntityNotFoundException {
        //Given
        String cinemaName = "IMAX";
        BigDecimal price = new BigDecimal("20");
        String nonExistingCinema = "Arta";

        Cinema cinema = sut.addCinema(cinemaName, price);

        //When
        Set<Seat> seats = sut.getAvailableSeats(nonExistingCinema);
    }

}
