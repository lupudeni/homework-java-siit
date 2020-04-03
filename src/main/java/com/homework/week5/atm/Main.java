package com.homework.week5.atm;

/**
 * Write an app that simulates an ATM machine.
 * Requirements:
 * the app should run indefinitely, allowing customers to enter their cards and withdraw money, deposit money, interogate sold and change pin.
 * class design (just a recomandation):
 * - User has one or more BankAccounts
 * - User has one or more Cards
 * - Cards are emitted to a single BankAccount
 * - User interacts with Cards via a withdraw(Card), deposit(Card), changePin(Card)
 * or interogateBalance(Card) action from ATM class based on the option he inputs to the keyboard.
 *
 * For testing this app manually, please use card number: 1 and pin: 0000
 * Have fun!
 */
public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        System.out.println("Welcome! Please log in order to use the ATM");
        Card card = atm.logIn();
        if (card == null) {
            System.out.println("Login has failed. Please try again later.");
        } else {
            atm.greetUser(card);
            atm.makeChoice(card);
        }

    }
}
