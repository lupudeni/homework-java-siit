package com.homework.week10.cinemareservation.repository;

import com.homework.week10.cinemareservation.entity.Cinema;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CinemaRepository {
    private final Map<String, Cinema> cinemas = new HashMap<>();
    public static final List<String> PREMIUM_SEATS = Arrays.asList("F3", "F4","F5","F6","F7","F8","G3","G4","G5","G6","G7","G8");

    public CinemaRepository() {
        populateCinemaDataBase();
    }

    //    public Cinema addCinema(String name, BigDecimal cinemaPrice) {
//        Cinema cinema = new Cinema(name, cinemaPrice);
//        cinemas.put(name, cinema);
//        return cinema;
//    }
//    public Cinema removeCinemaByName(String name) {
//        return cinemas.remove(name);
//    }

    private void populateCinemaDataBase() {
        cinemas.put("IMAX", new Cinema("IMAX", new BigDecimal("30")));
        cinemas.put("Cinema Arta", new Cinema("Cinema Arta",new BigDecimal("16")));
        cinemas.put("CineMagia", new Cinema("CineMagia", new BigDecimal("20")));
        cinemas.put("3DSuperCinema", new Cinema("3DSuperCinema", new BigDecimal("25")));
        cinemas.put("CineGold", new Cinema("CineGold", new BigDecimal("27")));
    }
}
