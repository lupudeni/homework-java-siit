package com.homework.week12.atm_new_with_DB_in_csv_files.front_end;

import com.homework.util.ActionStatus;
import com.homework.week12.atm_new_with_DB_in_csv_files.controller.BankController;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;

import javax.security.auth.login.LoginException;
import java.math.BigDecimal;
import java.util.Scanner;

public class ATM {
    private static BankController bankController = new BankController();

    public static void main(String[] args) {
        System.out.println("Welcome! Please log in order to use the ATM");
        try {
            Card card = logIn();
            System.out.println("Welcome, " + bankController.getUserName(card.getCardID()) + "!");
            useATM(card);
        } catch (LoginException e) {
            System.out.println("Login has failed. Please try again later.");
        }
    }

    private static Card logIn() throws LoginException {
        Scanner input = new Scanner(System.in);
        System.out.println("Card ID:");
        String cardId = input.next();
        System.out.println("Pin:");
        String pin = input.next();

        return bankController.logIn(cardId, pin);
    }

    private static void useATM(Card card) {
        Scanner input = new Scanner(System.in);
        ATM_USE: while(true) {
            printMenu();
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("balance : " + bankController.interrogateBalance(card));
                    break;
                case 2:
                    System.out.println("Enter amount: ");
                    BigDecimal depositAmount = input.nextBigDecimal();
                    System.out.println("Transaction status : " + bankController.deposit(card, depositAmount));
                    break;
                case 3:
                    System.out.println("Enter amount: ");
                    BigDecimal withdrawAmount = input.nextBigDecimal();
                    System.out.println("Transaction status : " + bankController.withdraw(card, withdrawAmount));
                    break;
                case 4:
                    System.out.println("Enter new pin: ");
                    String newPin = input.next();
                    System.out.println("PIN change status : " + bankController.changePin(card, newPin));
                    break;
                case 5:
                    System.out.println("Good Bye!");
                    break ATM_USE;
                default:
                    System.out.println(ActionStatus.BAD_INPUT);
            }
        }
    }

    private static void printMenu() {
        System.out.println("Please select an interaction:" +
                "\n1 --> Interrogate balance." +
                "\n2 --> Deposit cash." +
                "\n3 --> Withdraw cash." +
                "\n4 --> Change pin." +
                "\n5 --> Quit.");
    }
}
