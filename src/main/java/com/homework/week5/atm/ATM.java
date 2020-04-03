package com.homework.week5.atm;

import com.homework.util.ActionStatus;

import java.math.BigDecimal;
import java.util.Scanner;

public class ATM {
    private Scanner numericalInput;
    private Scanner textInput;
    private ElectronicBanking electronicBanking;

    public ATM() {
        this(new Scanner(System.in), new Scanner(System.in), new ElectronicBanking());
    }

    public ATM(Scanner numericalInput, Scanner textInput, ElectronicBanking electronicBanking) {
        this.numericalInput = numericalInput;
        this.textInput = textInput;
        this.electronicBanking = electronicBanking;
    }

    Card logIn() {
        System.out.println("Please input your card number: ");
        String cardNumber = textInput.next();
        System.out.println("Please input your pin: ");
        String pin = textInput.next();
        return electronicBanking.logIn(cardNumber, pin);
    }

    void makeChoice(Card card) {
        boolean continueOptions = true;

        while (continueOptions) {
            printMenu();
            int choice = numericalInput.nextInt();

            switch (choice) {
                case 1:
                    BigDecimal balance = electronicBanking.interrogateBalance(card);
                    System.out.println("balance = " + balance);
                    break;
                case 2:
                    System.out.println("Enter amount: ");
                    BigDecimal depositAmount = numericalInput.nextBigDecimal();
                    System.out.println("Transaction status : " + electronicBanking.deposit(card, depositAmount));
                    break;
                case 3:
                    System.out.println("Enter amount: ");
                    BigDecimal withdrawAmount = numericalInput.nextBigDecimal();
                    System.out.println("Transaction status : " + electronicBanking.withdraw(card, withdrawAmount));
                    break;
                case 4:
                    System.out.println("Enter new pin: ");
                    String newPin = textInput.next();
                    System.out.println("PIN change status : " + electronicBanking.changePin(card, newPin));
                    break;
                case 5:
                    System.out.println("Good Bye!");
                    continueOptions = false;
                    break;
                default:
                    System.out.println(ActionStatus.BAD_INPUT);
            }
        }
    }

    private void printMenu() {
        System.out.println("Please select an interaction:" +
                "\n1 --> Interrogate balance." +
                "\n2 --> Deposit cash." +
                "\n3 --> Withdraw cash." +
                "\n4 --> Change pin." +
                "\n5 --> Quit.");
    }

    void greetUser(Card card) {
        User user = electronicBanking.getUserByCard(card);
        if (user == null) {
            System.out.println("Hello");
        } else {
            System.out.println("Hello, " + user.getName() + "!");
        }
    }
}
