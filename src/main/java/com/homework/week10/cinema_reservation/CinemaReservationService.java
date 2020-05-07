package com.homework.week10.cinema_reservation;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CinemaReservationService {

    private Set<Cinema> cinemas;

    public CinemaReservationService(Set<Cinema> cinemas) {
        this.cinemas = new HashSet<>();
    }

    public boolean addCinema(Cinema cinema) {
        return cinemas.add(cinema);
    }

    public boolean removeCinema(Cinema cinema) {
        return cinemas.remove(cinema);
    }

    public boolean reserveSeat(Cinema cinema, String seatNumber) {
        return cinema.getSeats().stream()
                .anyMatch(seat -> {
                    if (!seat.isReserved()) {
                        seat.setReserved(true);
                        return true;
                    } else {
                        return false;
                    }
                });
    }

    public boolean cancelReservation(Cinema cinema, String seatNumber) {
        return cinema.getSeats().stream()
                .anyMatch(seat -> {
                    if (seat.isReserved()) {
                        seat.setReserved(false);
                        return true;
                    } else {
                        return false;
                    }
                });
    }
}
