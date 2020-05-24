package com.homework.week12.atm_new_with_DB_in_csv_files.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Card {
    private String number;
    private String pin;

    public Card(String number, String pin) {
        this.number = number;
        this.pin = pin;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPin() {
        return pin;
    }

    void setPin(String pin) {
        this.pin = pin;
    }
}
