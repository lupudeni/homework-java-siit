package com.homework.week12.atm_new_with_DB_in_csv_files.controller;

import com.homework.exception.EntityNotFoundException;
import com.homework.util.ActionStatus;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.BankAccount;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;
import com.homework.week12.atm_new_with_DB_in_csv_files.service.BankAccountService;
import com.homework.week12.atm_new_with_DB_in_csv_files.service.CardService;
import com.homework.week12.atm_new_with_DB_in_csv_files.service.UserService;

import javax.security.auth.login.LoginException;
import java.math.BigDecimal;

public class BankController {
    private final CardService cardService;
    private final BankAccountService bankAccountService;
    private final UserService userService;

    public BankController() {
        this.cardService = new CardService();
        this.bankAccountService = new BankAccountService(cardService);
        this.userService = new UserService(bankAccountService);
    }

    public BankController(CardService cardService, BankAccountService bankAccountService, UserService userService) {
        this.cardService = cardService;
        this.bankAccountService = bankAccountService;
        this.userService = userService;
    }

    public Card logIn(String cardId, String pin) throws LoginException {
        return cardService.logIn(cardId, pin);
    }

    public String changePin(Card card, String newPin) {
        return cardService.changePin(card, newPin);
    }

    public String interrogateBalance(Card card) {
        try {
            return bankAccountService.interrogateBalance(card).toString();
        } catch (EntityNotFoundException e) {
            return ActionStatus.FAIL + " - " + e.getMessage();
        }
    }

    public String deposit(Card card, BigDecimal amount) {
        try {
            return bankAccountService.deposit(card, amount);
        } catch (EntityNotFoundException e) {
            return ActionStatus.FAIL + " - " + e.getMessage();
        }
    }

    public String withdraw(Card card, BigDecimal amount) {

        if (amount != null && amount.compareTo(BigDecimal.ZERO) > 0 && card != null) {
            try {
                return bankAccountService.withdraw(card, amount);
            } catch (EntityNotFoundException e) {
                return ActionStatus.FAIL + " - " + e.getMessage();
            }
        }
        return ActionStatus.FAIL;
    }

    public String getUserName(String cardId) {
        try {
            BankAccount account = bankAccountService.getBankAccountByCardId(cardId);
            String userId = bankAccountService.getUserIdByAccount(account);
            return userService.getUserById(userId).getName();
        } catch (EntityNotFoundException e) {
            return ActionStatus.FAIL + " - " + e.getMessage();
        }
    }
}
