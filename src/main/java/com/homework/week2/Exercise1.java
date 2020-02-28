package com.homework.week2;

/**
 * Exercise 1: Calculate the sum of the first 100 prime numbers.
 * "A prime number (or a prime) is a natural number greater than 1 that cannot be formed by multiplying two smaller natural numbers."
 */
public class Exercise1 {
    public static void main(String[] args) {
        int primeCount = 0;
        int sum = 0;
        int n = 2;

        while (primeCount < 100) {
            if (isPrime(n)) {
                sum += n;
                primeCount++;
            }
            n++;
        }
        System.out.println("The sum of the first 100 prime numbers is " + sum + ".");
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
}

