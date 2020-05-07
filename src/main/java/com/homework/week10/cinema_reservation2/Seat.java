package com.homework.week10.cinema_reservation2;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Seat {
    private String seatNumber;
    private BigDecimal price;

}
