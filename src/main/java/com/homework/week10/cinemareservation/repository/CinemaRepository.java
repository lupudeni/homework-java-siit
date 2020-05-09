package com.homework.week10.cinemareservation.repository;

import com.homework.week10.cinemareservation.entity.Cinema;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CinemaRepository {
    private static final List<String> PREMIUM_SEATS = Arrays.asList("F3", "F4","F5","F6","F7","F8","G3","G4","G5","G6","G7","G8");
    private final Map<String, Cinema> cinemas = new HashMap<>();

    public Cinema getCinemaByName(String name) {
       return cinemas.get(name);
    }

    public boolean isPremiumSeat(String seatNr) {
       return PREMIUM_SEATS.contains(seatNr);
    }

    public void addCinema(Cinema cinema) {
        cinemas.put(cinema.getName(), cinema);
    }
}
