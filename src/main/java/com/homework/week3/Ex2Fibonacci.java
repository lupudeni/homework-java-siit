package com.homework.week3;

import java.util.Arrays;

public class Ex2Fibonacci {
    public static void main(String[] args) {
        fibonacci(25);
    }
    private static void fibonacci(int length) {
        int[] fibonacciArray = new int[length];
        fibonacciArray[0] = 0;
        fibonacciArray[1] = 1;
        for (int i = 2; i <= fibonacciArray.length - 1; i++) {
            fibonacciArray[i] = fibonacciArray[i - 1] + fibonacciArray[i - 2];
        }
        System.out.println(Arrays.toString(fibonacciArray));
    }

}
