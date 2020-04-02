package com.homework.week4.bank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class POS {
    private List<BankAccount> knownBankAccounts = new ArrayList<>();

    public void addKnownBankAccount(BankAccount account) {
        knownBankAccounts.add(account);
    }

    public String pay(BigDecimal amount, Card card) {
        BankAccount account = getBankAccountByCard(card);
        if (account != null && isCardValid(card)) {
            return account.withdrawMoney(amount);
        }
        return "Fail";
    }

    BankAccount getBankAccountByCard(Card card) {
        for (BankAccount account : knownBankAccounts) {
            boolean found = account.getAttachedCards().stream()
                    .anyMatch(cardNumber -> cardNumber.equals(card.getCardNumber()));
            if (found) {
                return account;
            }
        }
        return null;
    }

    private boolean isCardValid(Card card) {
        LocalDate currentDate = LocalDate.now();
        LocalDate expirationDate = card.getExpirationDate();
        return !expirationDate.isBefore(currentDate);
    }
}
