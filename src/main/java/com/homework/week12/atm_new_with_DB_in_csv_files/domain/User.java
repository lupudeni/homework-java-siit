package com.homework.week12.atm_new_with_DB_in_csv_files.domain;

import com.homework.week5.atm.BankAccount;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {
    private String userId;
    private String name;
    private List<BankAccount> bankAccount;
}
