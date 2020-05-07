package com.homework.week10.cinema_reservation;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Cinema> cinemas = new HashSet<>();
        CinemaReservationService cinemaReservationService = new CinemaReservationService(cinemas);

        cinemas.add(new Cinema("Cineplex", new BigDecimal(20)));
        cinemas.add(new Cinema("Movieplex Cinema", new BigDecimal(17)));
        cinemas.add(new Cinema("Hollywood Multiplex Operations", new BigDecimal(15)));
        cinemas.add(new Cinema("Grand Cinema", new BigDecimal(22)));
        cinemas.add(new Cinema("Cinema City", new BigDecimal(25)));

    }


}
