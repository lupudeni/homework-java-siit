package com.homework.week12.atm_new_with_DB_in_csv_files.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode
public class Card {
    private String cardID;
    private String pin;

    public Card(String cardID, String pin) {
        this.cardID = cardID;
        this.pin = pin;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getPin() {
        return pin;
    }

    void setPin(String pin) {
        this.pin = pin;
    }
}
