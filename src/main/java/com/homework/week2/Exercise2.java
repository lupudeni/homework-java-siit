package com.homework.week2;

import java.util.Scanner;

/**
 * Exercise 2: Display the smallest number from an array of number read from keyboard (2 versions: with normal for and with foreach)
 */
public class Exercise2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("How many elements would you like your array to have?");
        int userLength = input.nextInt();

        int[] userArray = new int[userLength];
        System.out.println("Please input the numbers you would like in your array:");
        for (int i = 0; i < userLength; i++) {
            userArray[i] = input.nextInt();
        }

        findSmallestNrVersion1(userArray);
        findSmallestNrVersion2(userArray);

    }

    private static void findSmallestNrVersion1(int[] userArray) {
        int min  = Integer.MAX_VALUE;
        for (int i = 0; i < userArray.length; i++) {
            if (userArray[i] < min) {
                min = userArray[i];
            }
        }
        System.out.println("Using a \"normal for\" statement: \nThe smallest nr. from your array is: " + min);
    }

    private static void findSmallestNrVersion2(int[] userArray) {
        int min = Integer.MAX_VALUE;
        for (int number : userArray) {
            if (number < min) {
                min = number;
            }
        }
        System.out.println("\nUsing a \"for-each\" statement: \nThe smallest nr. from your array is: " + min);
    }
}
