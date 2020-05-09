package com.homework.week10.cinemareservation;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Cinema {
    private String name;
    private Map<String, Seat> seats;

    public Cinema(String cinemaName, BigDecimal cinemaPrice) {
        this.name = cinemaName;
        this.seats = new HashMap<>();
        generateSeats(cinemaPrice);
    }

    public Seat getSeat(String seatNr) {
        return seats.get(seatNr);
    }

    public String getName() {
        return name;
    }

    private void generateSeats(BigDecimal cinemaPrice) {
        for (char ch = 'A'; ch <= 'J'; ch++) {
            for (int i = 1; i <= 10; i++) {
                String seatNr = "" + ch + i;
                BigDecimal seatPrice = generatePrice(seatNr, cinemaPrice);
                seats.put(seatNr, new Seat(seatNr, seatPrice));
            }
        }
    }

    BigDecimal generatePrice(String seatNumber, BigDecimal cinemaPrice) {
        if (CinemaReservationService.PREMIUM_SEATS.contains(seatNumber)) {
            return cinemaPrice.multiply(new BigDecimal("1.1"));
        } else {
            return cinemaPrice;
        }
    }

    public Set<Seat> getAvailableSeats() {
        return seats.values().stream()
                .filter(seat -> !seat.isReserved())
                .collect(Collectors.toSet());
    }
}
