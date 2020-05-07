package com.homework.week10.cinema_reservation2;


import java.util.HashMap;
import java.util.Map;

public class Cinema {
    private String name;
    private Map<String, Seat> seats;

    public Cinema(String cinemaName) {
        this.name = cinemaName;
        this.seats = new HashMap<>();

        for(char ch = 'A'; ch <= 'J'; ch++) {
            for (int i = 1; i <= 10; i++) {

            }
        }
    }



}
