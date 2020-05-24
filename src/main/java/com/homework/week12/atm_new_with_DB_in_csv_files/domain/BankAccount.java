package com.homework.week12.atm_new_with_DB_in_csv_files.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class BankAccount {
    private String accountID;
    private BigDecimal balance;
    private Card card;

}
