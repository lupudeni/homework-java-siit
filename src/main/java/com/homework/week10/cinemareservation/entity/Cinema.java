package com.homework.week10.cinemareservation.entity;

import java.util.HashMap;
import java.util.Map;

public class Cinema {
    private String name;
    private Map<String, Seat> seats;

    public Cinema(String cinemaName, Map<String, Seat> seats) {
        this.name = cinemaName;
        this.seats = seats;
    }

    public Seat getSeat(String seatNr) {
        return seats.get(seatNr);
    }

    public String getName() {
        return name;
    }

    public Map<String, Seat> getSeats() {
        return new HashMap<>(seats);
    }
}
