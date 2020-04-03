package com.homework.week5.atm;

import com.homework.util.ActionStatus;
import com.homework.week5.strings.StringManipulation;

import java.math.BigDecimal;

public class ElectronicBanking {
    private BankDataBase dataBase;
    private StringManipulation stringManipulation;

    public ElectronicBanking() {
        this(new BankDataBase(), new StringManipulation());
    }

    public ElectronicBanking(BankDataBase dataBase, StringManipulation stringManipulation) {
        this.dataBase = dataBase;
        this.stringManipulation = stringManipulation;
    }

    Card logIn(String cardNumber, String pin) {
        Card card = dataBase.getCardByNumber(cardNumber);
        if ((card == null) || !(isPinValid(card, pin))) {
            return null;
        }
        return card;
    }

    private boolean isPinValid(Card card, String pin) {
        return card.getPin().equals(pin);
    }

    BigDecimal interrogateBalance(Card card) {
        BankAccount account = dataBase.getBankAccountByCardNumber(card.getNumber());
        return account.getBalance();
    }

    String deposit(Card card, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            BankAccount account = dataBase.getBankAccountByCardNumber(card.getNumber());
            account.setBalance(account.getBalance().add(amount));
            return ActionStatus.SUCCESS;
        } else {
            return ActionStatus.FAIL;
        }
    }

    String withdraw(Card card, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            BankAccount account = dataBase.getBankAccountByCardNumber(card.getNumber());

            BigDecimal balance = account.getBalance();

            if (balance.compareTo(amount) >= 0) {
                BigDecimal newBalance = balance.subtract(amount);
                account.setBalance(newBalance);
                return ActionStatus.SUCCESS;
            }
        }
        return ActionStatus.FAIL;
    }

    String changePin(Card card, String newPin) {
        if (newPin.length() == 4 && stringManipulation.checkIfOnlyDigits(newPin)) {
            card.setPin(newPin);
            return ActionStatus.SUCCESS;
        }
        return ActionStatus.FAIL;
    }

    public User getUserByCard(Card card) {
        return dataBase.getUserByCard(card);
    }
}
