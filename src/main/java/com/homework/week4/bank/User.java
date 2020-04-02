package com.homework.week4.bank;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Card> cards = new ArrayList<>();
    private List<BankAccount> bankAccounts = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addBankAccount(BankAccount account) {
        bankAccounts.add(account);
    }
}
