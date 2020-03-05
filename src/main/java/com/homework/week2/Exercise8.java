package com.homework.week2;

import java.util.Scanner;

/**
 * Exercise 8: Calculate and display the factorial of a given number n. (ex: 4! = 1*2*3*4)
 */
public class Exercise8 {
    public static void main(String[] args) {
        int number = getNumber();
        getFactorial(number);
    }

    private static void getFactorial(int number) {
        int factorial = 1;
        for(int i = 1; i <= number; i++) {
            factorial = factorial * i;
        }
        System.out.println(number + "! = " + factorial);
    }

    private static int getNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input a number: ");
        return input.nextInt();
    }
}
