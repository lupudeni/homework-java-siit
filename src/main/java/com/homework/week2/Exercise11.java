package com.homework.week2;

import java.util.Scanner;

/**
 * Exercise 11: Print the number of days specific to each month in year.
 * input: Month (string) or index of the month(1, 2, ... 12)
 * output: 31, 30, 28
 */

public class Exercise11 {
    public static void main(String[] args) {
        int month = readInput();
        getNumberOfDays(month);
    }

    private static int readInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type in the month in numbers: ");
        return scanner.nextInt();
    }

    private static void getNumberOfDays(int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 12:
            case 10:
                System.out.println("31 days");
                break;
            case 2:
                System.out.println("28 or 29 days");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println("30 days");
                break;
            default:
                System.out.println("Bad number!");
        }
    }
}
