package com.homework.week10.cinemareservation.service;

import com.homework.exception.EntityNotFoundException;
import com.homework.week10.cinemareservation.entity.Cinema;
import com.homework.week10.cinemareservation.entity.Seat;
import com.homework.week10.cinemareservation.repository.CinemaRepository;

import java.util.Set;

public class CinemaService {
    private final CinemaRepository cinemaRepository = new CinemaRepository();

    public Seat getSeatByCinema(String cinemaName, String seatNumber) throws EntityNotFoundException{
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

    public Set<Seat> getAvailableSeats(String cinemaName) throws EntityNotFoundException{
        if (cinemaName == null) {
            throw new IllegalArgumentException("Cinema name field must not be null");
        }
        Cinema cinema = cinemaRepository.getCinemaByName(cinemaName);
        if (cinema == null) {
            throw new EntityNotFoundException("Cinema " + cinemaName + " not found");
        }
        return cinema.getAvailableSeats();
    }


}
