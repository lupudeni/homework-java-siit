package com.homework.week2;

import java.util.Scanner;

/**
 * Exercise 7: Change the implementation of the calculator from the previous meeting to not stop after one calculation.
 * (Hint: put switch inside a while loop)
 */
public class Exercise7 {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean continueCalculation = decision();

        while (continueCalculation) {

            System.out.println("Type in the numbers: ");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println("Now select an operation type (\"+\", \"-\", \"*\", \"/\" or \"%\").");
            String operation = scanner.next();
            switch (operation) {
                case "+":
                    System.out.println("The sum is " + (a + b));
                    continueCalculation = decision();
                    break;
                case "-":
                    System.out.println("The difference is " + (a - b));
                    continueCalculation = decision();
                    break;
                case "*":
                    System.out.println("The product is " + (a * b));
                    continueCalculation = decision();
                    break;
                case "/":
                    System.out.println("The division is " + (a / b));
                    continueCalculation = decision();
                    break;
                case "%":
                    System.out.println("The rest of the division is " + (a % b));
                    continueCalculation = decision();
                    break;
                default:
                    System.out.println("Bad operation :(");
                    continueCalculation = decision();
            }
        }
    }

    public static boolean decision() {
        while (true) {
            System.out.println("Would you like to perform a calculation? Type \'Yes\' or \'No\'.");
            String answer = scanner.next();
            if (answer.equalsIgnoreCase("Yes")) {
                return true;
            } else if (answer.equalsIgnoreCase("No")) {
                System.out.println("Ow, sorry to hear that :(. Bye bye!");
                return false;
            } else {
                System.out.println("Bad choice of words! Let's stick to \'Yes\' or \'No\', shall we?");
            }
        }
    }
}
