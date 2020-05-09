package com.homework.week10.electronicvote.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Citizen {
    private String name;
    private String surname; //nume de familie
    private String address;
    private IDCard personalID;
    private boolean voted;

    public String getCNP() {
        return personalID.getCnp();
    }

}
