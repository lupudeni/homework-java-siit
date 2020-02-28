package com.homework.week2;

import java.util.Scanner;

/**
 * Exercise 3: Display the max digit from a number.
 */
public class Exercise3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input a number:");
        int number = input.nextInt();
        number = Math.abs(number);
        int digit;
        int max = Integer.MIN_VALUE;

        if (number == 0) {
            max = 0;
        }
        while (number != 0) {
            digit = number % 10;
            if (digit > max) {
                max = digit;
            }
            number /= 10;
        }

        System.out.println("max digit = " + max);
    }
}

