package com.homework.week10.cinema_reservation;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * "Every cinema has a sweet spot which the designers were working towards and typically this is 2/3rds the way back in the middle of the row.
 * Too far back and you miss out on some of the acoustics and the screen is occupying less of your vision.
 * Too far forward and you are overloaded with the front speakers and the screen takes up too much of your vision.
 * Too far to the side and you are getting too much left or right audio.
 * If the screen seating is heavily raked (steep angle) then the sweet spot may be in the middle rather than 2/3rds back."
 * Answer provided by a Media technology professional on https://www.quora.com/What-are-the-best-seats-in-a-movie-theater
 * Taking this into consideration and using an approximation, the best places in one of our cinemas are from row F to G, seats 3 to 8.
 * Therefore, the pricing on these seats will be the basic price of the cinema + 10%.
 */
@EqualsAndHashCode
public class Seat {
    private final String seatNumber;
    private final BigDecimal price;
    private boolean reserved;
    private final List<String> premiumSeats = Arrays.asList("F3", "F4","F5","F6","F7","F8","G3","G4","G5","G6","G7","G8");

    public Seat(String seatNumber, BigDecimal cinemaBasicPrice) {
        this.seatNumber = seatNumber;
        this.reserved = false;
//this should be done by something else
        if (premiumSeats.contains(seatNumber)) {
            this.price = cinemaBasicPrice.multiply(new BigDecimal("1.1"));
        } else {
            this.price = cinemaBasicPrice;
        }
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}

