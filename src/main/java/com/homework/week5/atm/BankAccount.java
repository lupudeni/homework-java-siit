package com.homework.week5.atm;

import java.math.BigDecimal;

public class BankAccount {
    private BigDecimal balance;
    private Card card;

    public BankAccount(BigDecimal balance, Card card) {
        this.balance = balance;
        this.card = card;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
