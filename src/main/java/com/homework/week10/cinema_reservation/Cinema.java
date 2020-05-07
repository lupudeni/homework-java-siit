package com.homework.week10.cinema_reservation;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
public class Cinema {
    private final String cinemaName;
    private final Set<Seat> seats;
    private final BigDecimal cinemaBasicPrice;

    public Cinema(String cinemaName, BigDecimal cinemaBasicPrice) {
        this.cinemaName = cinemaName;
        this.cinemaBasicPrice = cinemaBasicPrice;
        this.seats = new HashSet<Seat>();

        for(char ch = 'A'; ch <= 'J'; ch++) {
            for (int i = 1; i <= 10; i++) {
                seats.add(new Seat("" + ch + i, cinemaBasicPrice));
            }
        }
    }

    public Set<Seat> getSeats() {
        return new HashSet<Seat>(this.seats);
    }
}
