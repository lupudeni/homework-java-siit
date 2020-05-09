package com.homework.week10.cinemareservation;

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
    public static final List<String> PREMIUM_SEATS = Arrays.asList("F3", "F4","F5","F6","F7","F8","G3","G4","G5","G6","G7","G8");


    private final Map<String, Cinema> cinemas;

    public CinemaReservationService() {
        this.cinemas = new HashMap<>();
    }

    public Cinema addCinema(String name, BigDecimal cinemaPrice) {
        Cinema cinema = new Cinema(name, cinemaPrice);
        cinemas.put(name, cinema);
        return cinema;
    }

    public Cinema removeCinema(String name) {
        return cinemas.remove(name);
    }

    public Map<String, Cinema> getCinemas() {
        return new HashMap<>(cinemas);
    }

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
