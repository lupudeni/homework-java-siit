package com.homework.week10.electronicvote.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IDCard {
    private String cnp;
    private String idSerial;
    private String idNumber;
}
