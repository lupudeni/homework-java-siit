package com.homework.week10.cinemareservation;

        import com.homework.week10.cinemareservation.service.CinemaReservationService;

public class Main {
    public static void main(String[] args) {
        CinemaReservationService cinemaReservationService = new CinemaReservationService();

        System.out.println(cinemaReservationService.getAvailableSeats("IMAX"));
    }
}
