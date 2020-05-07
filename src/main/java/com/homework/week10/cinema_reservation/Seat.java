package com.homework.week10.cinema_reservation;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Seat {
    private String seatNumber;
    private BigDecimal price;
    private boolean reserved;


    public Seat(String seatNumber, BigDecimal seatPrice) {
        this.seatNumber = seatNumber;
        this.price = seatPrice;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

}
