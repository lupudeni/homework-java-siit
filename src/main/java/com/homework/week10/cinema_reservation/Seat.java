package com.homework.week10.cinema_reservation;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class Seat {
    private String seatNumber;
    private BigDecimal price;
    private boolean reserved;


    public Seat(String seatNumber, BigDecimal cinemaPrice) {
        this.seatNumber = seatNumber;
        if (CinemaReservationService.PREMIUM_SEATS.contains(seatNumber)) {
            this.price = cinemaPrice.multiply(new BigDecimal("1.1"));
        } else {
            this.price = cinemaPrice;
        }
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

}
