package com.homework.week5;

import java.util.Scanner;

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
 */
public class ATM {
    private BankDataBase dataBase = new BankDataBase();

    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner stringInput = new Scanner(System.in);
        Scanner nrInput = new Scanner(System.in);

        boolean continueAtm = true;
        while (continueAtm) {
            System.out.println("Please input your card number: ");
            String cardNumber = stringInput.next();
            System.out.println("Please input your pin: ");
            String pin = stringInput.next();
            Card card = atm.logIn(cardNumber, pin);
            if (card == null) {
                System.out.println("Login failed. Please try again.");
                continue;
            }

            printMenu();
            int option = nrInput.nextInt();
            switch (option) {
                case 1:
                    double balance = atm.interrogateBalance(card);
                    System.out.println("balance = " + balance);
                    break;
                case 2:
                    System.out.println("Enter amount: ");
                    double amount = nrInput.nextDouble();
                    atm.deposit(card, amount);
                    break;
                case 3:
                    System.out.println("Enter amount: ");
                    double amountW = nrInput.nextDouble();
                    atm.withdraw(card, amountW);
                    break;
                case 4:
                    System.out.println("Enter new pin: ");
                    String newPin = stringInput.next();
                    atm.changePin(card, newPin);
                    break;
                case 5:
                    continueAtm = false;
                    break;
                default:
                    System.out.println("Please select a valid option");
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

    private double interrogateBalance(Card card) {
        BankAccount account = dataBase.getBankAccountByCardNumber(card.getNumber());
        return account.getBalance();
    }

    private Card logIn(String cardNumber, String pin) {
        Card card = dataBase.getCardByNumber(cardNumber);

        if ((card == null) || !(verifyPin(card, pin))) {
            return null;
        }
        return card;
    }

    private boolean verifyPin(Card card, String pin) {
        return card.getPin().equals(pin);
    }

    private void deposit(Card card, double amount) {
        if (amount > 0) {
            BankAccount account = dataBase.getBankAccountByCardNumber(card.getNumber());
            account.setBalance(account.getBalance() + amount);
        } else {
            System.out.println("Amount must be greater than 0.");
        }
    }

    private void withdraw(Card card, double amount) {
        if (amount > 0) {
            BankAccount account = dataBase.getBankAccountByCardNumber(card.getNumber());

            double balance = account.getBalance();
            if ((balance - amount) > 0) {
                account.setBalance(balance - amount);
            } else {
                System.out.println("Insufficient funds.");
            }

        } else {
            System.out.println("Amount must be greater than 0.");
        }
    }

    private void changePin(Card card, String newPin) {
        card.setPin(newPin);
    }
}
