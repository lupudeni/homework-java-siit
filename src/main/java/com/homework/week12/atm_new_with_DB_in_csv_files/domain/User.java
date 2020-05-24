package com.homework.week12.atm_new_with_DB_in_csv_files.domain;

import com.homework.week5.atm.BankAccount;
import com.homework.week5.atm.Card;
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
