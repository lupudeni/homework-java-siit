package com.homework.week10.electronicvote;
//- CNP (String) - limited nr of chars
////                         - ID series (String)
////
// - nr (String) - limited nr. of chars

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IDCard {
    private String cnp;
    private String idSerial;
    private String idNumber;


}
