package com.homework.week12.atm_new_with_DB_in_csv_files.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Card {
    private String cardID;
    private String pin;

}
