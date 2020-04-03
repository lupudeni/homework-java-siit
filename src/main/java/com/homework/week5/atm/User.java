package com.homework.week5.atm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private List<BankAccount> bankAccount;
    private List<Card> cards;
}
