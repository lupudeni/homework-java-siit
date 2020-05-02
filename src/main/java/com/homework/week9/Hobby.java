package com.homework.week9;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Hobby {
    private String hobbyName;
    private int frequency; //per week
    private List<Address> addresses;

    private Hobby(String hobbyName, int frequency, List<Address> addresses) {
        this.hobbyName = hobbyName;
        this.frequency = frequency;
        this.addresses = addresses;
    }

}
