package com.homework.week4.bank;

import java.time.LocalDate;

public class Card {
    private String cardNumber;
    private LocalDate expirationDate;
    private String cardOwner;

    public Card(String cardNumber, LocalDate expirationDate, String cardOwner) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardOwner = cardOwner;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getCardOwner() {
        return cardOwner;
    }

}
