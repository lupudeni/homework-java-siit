package com.homework.week12.atm_new_with_DB_in_csv_files.domain;

import com.homework.week5.atm.Card;

import java.math.BigDecimal;

public class BankAccount {
    private BigDecimal balance;
    private com.homework.week5.atm.Card card;

    public BankAccount(BigDecimal balance, com.homework.week5.atm.Card card) {
        this.balance = balance;
        this.card = card;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public com.homework.week5.atm.Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
