package com.homework.week2;

import java.util.Scanner;

/**
 * Exercise 4: Check if a number is a palindrome ( e.g palindrome 1221, 34143)
 */
public class Exercise4 {
    public static void main(String[] args) {
        int number = getNumber();

        String digits = Integer.toString(Math.abs(number));

        if (isPalindrome(digits)) {
            System.out.println(number + " is a palindrome.");
        } else {
            System.out.println(number + " is NOT a palindrome.");
        }

    }

    private static int getNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input a number:");
        return input.nextInt();
    }

    private static boolean isPalindrome(String digits) {
        int fin = digits.length() - 1;
        for (int i = 0; i <= (digits.length() / 2); i++) {
            if (digits.charAt(i) != digits.charAt(fin)) {
                return false;
            }
            fin--;
        }
        return true;
    }
}
