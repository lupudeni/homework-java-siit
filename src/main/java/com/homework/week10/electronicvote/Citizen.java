package com.homework.week10.electronicvote;

import lombok.Data;

@Data
public class Citizen {
    private String name;
    private String surname; //nume de familie
    private String Address;
    private IDCard personalID;
    private boolean voted;

    public Citizen(String name, String surname, String address, IDCard personalID) {
        this.surname = surname;
        this.name = name;
        Address = address;
        this.personalID = personalID;
        voted = false;
    }

    public String getCNP() {
        return personalID.getCnp();
    }

}
