package com.homework.week12.atm_new_with_DB_in_csv_files.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
@Builder
@EqualsAndHashCode
public class BankAccount {
    private String accountID;
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

    public String getAccountID() {
        return accountID;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
