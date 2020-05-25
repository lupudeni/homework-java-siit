package com.homework.week12.atm_new_with_DB_in_csv_files.service;

import com.homework.exception.DatabaseException;
import com.homework.exception.EntityNotFoundException;
import com.homework.util.ActionStatus;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.BankAccount;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;
import com.homework.week12.atm_new_with_DB_in_csv_files.repository.BankAccountRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(CardService cardService) {
        bankAccountRepository = new BankAccountRepository(cardService.getCardMap());
    }

    public BigDecimal interrogateBalance(Card card) throws EntityNotFoundException {
        BankAccount account = getBankAccountByCardId(card.getCardID());
        return account.getBalance();
    }

    public String deposit(Card card, BigDecimal amount) throws EntityNotFoundException {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            BankAccount account = bankAccountRepository
                    .getBankAccountByCardId(card.getCardID())
                    .orElseThrow(() -> new EntityNotFoundException("No result for card id '" + card.getCardID() + "'"));
            account.setBalance(account.getBalance().add(amount));

            try {
                bankAccountRepository.update(account);
            } catch (DatabaseException e) {
                return ActionStatus.FAIL + " " + e.getMessage();
            }
            return ActionStatus.SUCCESS;
        }
        return ActionStatus.FAIL;
    }

    public String withdraw(Card card, BigDecimal amount) throws EntityNotFoundException {
        BankAccount account = getBankAccountByCardId(card.getCardID());

        BigDecimal balance = account.getBalance();
        if (balance.compareTo(amount) < 0) {
            return ActionStatus.FAIL;
        }
        BigDecimal newBalance = balance.subtract(amount);
        account.setBalance(newBalance);

        try {
            bankAccountRepository.update(account);
        } catch (DatabaseException e) {
            return ActionStatus.FAIL + " " + e.getMessage();
        }
        return ActionStatus.SUCCESS;
    }

    public BankAccount getBankAccountByCardId(String cardId) throws EntityNotFoundException {
        return bankAccountRepository.getBankAccountByCardId(cardId).orElseThrow(() ->
                new EntityNotFoundException("No result for card id '" + cardId + "'"));
    }

    public String getUserIdByAccount(BankAccount account) throws EntityNotFoundException {
        return bankAccountRepository.getUserIdByAccount(account);
    }

    public Map<String, List<BankAccount>> getUserIdToAccounts() {
        return bankAccountRepository.getUserIdToAccounts();
    }
}

