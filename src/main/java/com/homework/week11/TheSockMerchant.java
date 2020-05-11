package com.homework.week11;

import java.util.*;
import java.util.stream.Collectors;

public class TheSockMerchant {

    public int sockMerchant(int[] socks) {
        if (socks == null || socks.length == 0) {
            return 0;
        }
        return  Arrays.stream(socks)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue))
                .values()
                .stream()
                .map(socksByColor -> socksByColor.size() / 2)
                .reduce(0, Integer::sum);
    }
}
