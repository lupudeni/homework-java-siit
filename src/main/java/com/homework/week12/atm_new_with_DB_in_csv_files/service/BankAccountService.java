package com.homework.week12.atm_new_with_DB_in_csv_files.service;

import com.homework.exception.EntityNotFoundException;
import com.homework.util.ActionStatus;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.BankAccount;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;
import com.homework.week12.atm_new_with_DB_in_csv_files.repository.BankAccountRepository;

import java.math.BigDecimal;
import java.util.Map;

public class BankAccountService {
    private final CardService cardService;
    private final BankAccountRepository bankAccountRepository;

    public BankAccountService() {
        this(new CardService());
    }

    public BankAccountService(CardService cardService) {
        this.cardService = cardService;
        Map<String, Card> cardMap = cardService.getCardMap();
        bankAccountRepository = new BankAccountRepository(cardMap);
    }

    public BigDecimal interrogateBalance(Card card) throws EntityNotFoundException {
       BankAccount account = bankAccountRepository
               .getBankAccountByCardId(card.getCardID())
               .orElseThrow(() -> new EntityNotFoundException("No result for card id '" + card.getCardID() + "'"));
       return account.getBalance();
    }

    public String deposit(Card card, BigDecimal amount) throws EntityNotFoundException {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            BankAccount account = bankAccountRepository
                    .getBankAccountByCardId(card.getCardID())
                    .orElseThrow(() -> new EntityNotFoundException("No result for card id '" + card.getCardID() + "'"));
            account.setBalance(account.getBalance().add(amount));
            return ActionStatus.SUCCESS;
        }
        return ActionStatus.FAIL;
    }
}

