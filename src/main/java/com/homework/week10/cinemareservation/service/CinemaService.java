package com.homework.week10.cinemareservation.service;

import com.homework.exception.EntityNotFoundException;
import com.homework.week10.cinemareservation.entity.Cinema;
import com.homework.week10.cinemareservation.entity.Seat;
import com.homework.week10.cinemareservation.repository.CinemaRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CinemaService {
    private final CinemaRepository cinemaRepository;

    public CinemaService() {
        cinemaRepository = new CinemaRepository();
    }

    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public Cinema addCinema(String name, BigDecimal price) {
        if (name == null || price == null) {
            throw new IllegalArgumentException("Fields cannot be null!");
        }
        Map<String, Seat> seats = generateSeats(price);
        Cinema cinema = new Cinema(name, seats);
        cinemaRepository.addCinema(cinema);
        return cinema;
    }

    private Map<String, Seat> generateSeats(BigDecimal cinemaPrice) {
        Map<String, Seat> seats = new HashMap<>();
        for (char ch = 'A'; ch <= 'J'; ch++) {
            for (int i = 1; i <= 10; i++) {
                String seatNr = "" + ch + i;
                BigDecimal seatPrice = generatePrice(seatNr, cinemaPrice);
                seats.put(seatNr, new Seat(seatNr, seatPrice));
            }
        }
        return seats;
    }

    BigDecimal generatePrice(String seatNumber, BigDecimal cinemaPrice) {
        if (cinemaRepository.isPremiumSeat(seatNumber)) {
            return cinemaPrice.multiply(new BigDecimal("1.1"));
        } else {
            return cinemaPrice;
        }
    }

    public Seat getSeatByCinema(String cinemaName, String seatNumber) throws EntityNotFoundException {
        if (cinemaName == null || seatNumber == null) {
            throw new IllegalArgumentException("Fields must not be null");
        }
        Cinema cinema = cinemaRepository.getCinemaByName(cinemaName);
        if (cinema == null) {
            throw new EntityNotFoundException("Cinema " + cinemaName + " not found");
        }
        Seat seat = cinema.getSeat(seatNumber);
        if (seat == null) {
            throw new EntityNotFoundException("Seat nr. " + seatNumber + " not found");
        }
        return seat;
    }

    public Set<Seat> getAvailableSeats(String cinemaName) throws EntityNotFoundException {
        if (cinemaName == null) {
            throw new IllegalArgumentException("Cinema name field must not be null");
        }
        Cinema cinema = cinemaRepository.getCinemaByName(cinemaName);
        if (cinema == null) {
            throw new EntityNotFoundException("Cinema " + cinemaName + " not found");
        }
        return getAvailableSeatsByCinema(cinema);
    }

    private Set<Seat> getAvailableSeatsByCinema(Cinema cinema) {
        return cinema.getSeats().values().stream()
                .filter(seat -> !seat.isReserved())
                .collect(Collectors.toSet());
    }
}
