package com.homework.week10.cinemareservation.service;

import com.homework.week10.cinemareservation.entity.Cinema;
import com.homework.week10.cinemareservation.entity.Seat;
import com.homework.week10.cinemareservation.repository.CinemaRepository;

import java.math.BigDecimal;
import java.util.*;

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

public class CinemaReservationService {
    public static final String RESERVATION_COMPLETE = "Reservation successfully completed";
    public static final String RESERVATION_FAILED = "Reservation failed";
    public static final String RESERVATION_DELETED = "Reservation successfully deleted";
    public static final String NO_RESERVATION = "No reservation found.";
    private final CinemaRepository cinemaRepository = new CinemaRepository();

    public String reserveSeatAtCinema(String cinemaName, String seatNumber) {
        Seat seat = getSeatByCinema(cinemaName, seatNumber);
        if (seat == null || seat.isReserved()) {
            return RESERVATION_FAILED;
        }
        seat.setReserved(true);
        return RESERVATION_COMPLETE;
    }

    public String deleteReservation(String cinemaName, String seatNumber) {
        Seat seat = getSeatByCinema(cinemaName, seatNumber);
        if (seat == null || !seat.isReserved()) {
            return NO_RESERVATION;
        }
        seat.setReserved(false);
        return RESERVATION_DELETED;
    }

    public Seat getSeatByCinema(String cinemaName, String seatNumber) {
        Cinema cinema = cinemas.get(cinemaName);
        if (cinema == null) {
            return null;
        }
        return cinema.getSeat(seatNumber);
    }

    public Set<Seat> getAvailableSeats(String cinemaName) {
        Cinema cinema = cinemas.get(cinemaName);
        if (cinema == null) {
            return Collections.emptySet();
        }
        return cinema.getAvailableSeats();
    }
}
