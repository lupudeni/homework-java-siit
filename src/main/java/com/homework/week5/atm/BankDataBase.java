package com.homework.week5.atm;

import java.util.ArrayList;
import java.util.List;

public class BankDataBase {
    private List<User> users = new ArrayList<>();
    private List<BankAccount> bankAccounts = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();

    public BankDataBase() {
        Card card1 = new Card("1", "0089");
        Card card2 = new Card("2", "9089");
        Card card3 = new Card("3", "5687");
        Card card4 = new Card("4", "0001");

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);


        BankAccount account1 = new BankAccount(5000, card1);
        BankAccount account2 = new BankAccount(67936.6, card2);
        BankAccount account3 = new BankAccount(30.2, card3);
        BankAccount account4 = new BankAccount(90000, card4);

        bankAccounts.add(account1);
        bankAccounts.add(account2);
        bankAccounts.add(account3);
        bankAccounts.add(account4);


        List<BankAccount> user1Accounts = new ArrayList<>();
        user1Accounts.add(account1);
        user1Accounts.add(account2);
        User user1 = new User("Costel", user1Accounts);

        List<BankAccount> user2Accounts = new ArrayList<>();
        user1Accounts.add(account3);
        User user2 = new User("Nelu", user2Accounts);

        List<BankAccount> user3Accounts = new ArrayList<>();
        user1Accounts.add(account4);
        User user3 = new User("Barky", user3Accounts);

        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    public Card getCardByNumber(String cardNumber) {
        if (cardNumber == null) {
            return null;
        }
        for (Card card : cards) {
            if (card.getNumber().equals(cardNumber)) {
                return card;
            }
        }
        return null;
    }

    public BankAccount getBankAccountByCardNumber(String cardNumber) {
        if (cardNumber == null) {
            return null;
        }
        for (BankAccount account : bankAccounts) {
            if (account.getCard().getNumber().equals(cardNumber)) {
                return account;
            }
        }
        return null;
    }
}
