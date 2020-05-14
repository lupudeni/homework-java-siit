package com.homework.week11;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Stream;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(generateFirst100Fibonacci()));
    }

    public static BigInteger[] generateFirst100Fibonacci() {
        return Stream.iterate(new BigInteger[]{BigInteger.ZERO, BigInteger.ONE}, myInitialArray
                -> new BigInteger[]{myInitialArray[1], myInitialArray[0].add(myInitialArray[1])})
                .limit(100)
                .map(resultingArray -> resultingArray[0])
                .toArray(BigInteger[]::new);
    }
}
