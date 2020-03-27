package com.homework.week5;

public class BankAccount {
    private double balance;
    private Card card;

    public BankAccount(double balance, Card card) {
        this.balance = balance;
        this.card = card;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
