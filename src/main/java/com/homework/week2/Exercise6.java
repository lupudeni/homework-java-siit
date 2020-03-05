package com.homework.week2;

import java.util.Scanner;

/**
 * Exercise 6: Change the implementation of the calculator from the previous meeting to use only if-else instead of switch.
 */
public class Exercise6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input two numbers:");
        int a = input.nextInt();
        int b = input.nextInt();
        System.out.println("Now select an operation type (\"+\", \"-\", \"*\", \"/\" or \"%\").");
        String operation = input.next();

        performOperation(a, b, operation);
    }

    private static void performOperation(int a, int b, String operation) {
        if (operation.equals("+")) {
            System.out.println(a + " + " + b + " = " + (a + b));
        } else if (operation.equals("-")) {
            System.out.println(a + " - " + b + " = " + (a - b));
        } else if (operation.equals("*")) {
            System.out.println(a + " * " + b + " = " + (a * b));
        } else if (operation.equals("/")) {
            if (b == 0 ){
                System.out.println("Bad operation! No 0 for the \"/\" please!");
            } else {
                System.out.println(a + " / " + b + " = " + (a / b));
            }
        } else if (operation.equals("%")) {
            if (b == 0 ){
                System.out.println("Bad operation! No 0 for the \"%\" please!");
            } else {
                System.out.println(a + " % " + b + " = " + (a % b));
            }
        } else {
            System.out.println("Bad input! Please input just one of the suggestions mentioned in the parenthesis!");
        }
    }
}
