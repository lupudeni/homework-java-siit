package com.homework.week5.atm;

import java.util.List;

public class User {
    private String name;
    private List<BankAccount> bankAccount;

    public User(String name, List<BankAccount> bankAccount) {
        this.name = name;
        this.bankAccount = bankAccount;
    }
}
