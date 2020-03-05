package com.homework.week2;

import java.util.Scanner;

/**
 * Exercise 5: Display all the prime numbers lower than a given number.
 */
public class Exercise5 {
    public static void main(String[] args) {
        int number = getNumber();

        displayPrimeNumbers(number);
    }

    private static int getNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input a number:");
        return input.nextInt();
    }

    private static void displayPrimeNumbers(int number) {
        if (number <= 2) {
            System.out.println("There are no prime numbers lower than " + number + "." + "" +
                    "\n\"A prime number (or a prime) is a natural number greater than 1 " +
                    "\nthat cannot be formed by multiplying two smaller natural numbers.\"");
        } else {
            for (int n = (number - 1); n > 1; n--) {
                if (isPrime(n)) {
                    System.out.println(n);
                }
            }
        }
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
