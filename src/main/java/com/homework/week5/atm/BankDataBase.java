package com.homework.week5.atm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankDataBase {
    private List<User> users;
    private List<BankAccount> bankAccounts;
    private List<Card> cards;

    public BankDataBase(List<User> users, List<BankAccount> bankAccounts, List<Card> cards) {
        this.users = users;
        this.bankAccounts = bankAccounts;
        this.cards = cards;
    }

    public BankDataBase() {
        initialiseDataBase();
    }

    public void initialiseDataBase() {
        Card card1 = new Card("1", "0000");
        Card card2 = new Card("2", "9089");
        Card card3 = new Card("3", "5687");
        Card card4 = new Card("4", "0001");

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);


        BankAccount account1 = new BankAccount(new BigDecimal(5000), card1);
        BankAccount account2 = new BankAccount(new BigDecimal("67936.6"), card2);
        BankAccount account3 = new BankAccount(new BigDecimal("30.2"), card3);
        BankAccount account4 = new BankAccount(new BigDecimal(90000), card4);

        bankAccounts.add(account1);
        bankAccounts.add(account2);
        bankAccounts.add(account3);
        bankAccounts.add(account4);


        List<BankAccount> user1Accounts = new ArrayList<>();
        user1Accounts.add(account1);
        user1Accounts.add(account2);
        List<Card> user1Cards = new ArrayList<>();
        user1Cards.add(card1);
        user1Cards.add(card2);
        User user1 = new User("Ionut", user1Accounts, user1Cards);

        List<BankAccount> user2Accounts = new ArrayList<>();
        user1Accounts.add(account3);
        List<Card> user2Cards = new ArrayList<>();
        user2Cards.add(card3);
        User user2 = new User("Putin", user2Accounts, user2Cards);

        List<BankAccount> user3Accounts = new ArrayList<>();
        user1Accounts.add(account4);
        List<Card> user3Cards = new ArrayList<>();
        user3Cards.add(card4);
        User user3 = new User("Barky", user3Accounts, user3Cards);

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

    public User getUserByCard(Card card) {
        if (card == null) {
            return null;
        }
        return users.stream()
                .filter(user -> user.getCards().contains(card))
                .findFirst()
                .orElse(null);

    }
}
