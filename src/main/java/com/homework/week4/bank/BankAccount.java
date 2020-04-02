package com.homework.week4.bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String iban;
    private BigDecimal balance;
    private List<String> attachedCards = new ArrayList<>();


    public BankAccount(String iban) {
        this.iban = iban;
    }

    public void attachCard(String cardNumber) {
        attachedCards.add(cardNumber);
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String addMoney(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) >= 0) {
            balance = balance.add(amount);
            return "Success";
        }
        return "Fail";
    }

    String withdrawMoney(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            return "Success";
        }
        return "Fail";
    }

    public String getIban() {
        return iban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<String> getAttachedCards() {
        return attachedCards;
    }

}
