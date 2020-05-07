package com.homework.week10.cinema_reservation;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cinema {
    private String name;
    private Map<String, Seat> seats;
    private BigDecimal cinemaPrice;

    public Cinema(String cinemaName, BigDecimal cinemaPrice) {
        this.name = cinemaName;
        this.seats = new HashMap<>();

        for(char ch = 'A'; ch <= 'J'; ch++) {
            for (int i = 1; i <= 10; i++) {
                String seatNr = "" + ch + i;
                seats.put(seatNr, new Seat(seatNr, cinemaPrice));
            }
        }
    }

    public Seat getSeat(String seatNr) {
       return seats.get(seatNr);
    }

    public Map<String, Seat> getSeats() {
        return seats;
    }


}
