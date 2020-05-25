package com.homework.week12.atm_new_with_DB_in_csv_files.service;

import com.homework.exception.DatabaseException;
import com.homework.exception.EntityNotFoundException;
import com.homework.util.ActionStatus;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.Card;
import com.homework.week12.atm_new_with_DB_in_csv_files.repository.CardRepository;
import com.homework.week5.strings.StringManipulation;

import javax.security.auth.login.LoginException;
import java.util.Map;

public class CardService {
    private final CardRepository cardRepository;
    private final StringManipulation stringManipulation;

    public CardService() {
        this(new CardRepository(), new StringManipulation());
    }

    public CardService(CardRepository cardRepository, StringManipulation stringManipulation) {
        this.cardRepository = cardRepository;
        this.stringManipulation = stringManipulation;
    }

    Map<String, Card> getCardMap() {
        return cardRepository.getCardMap();
    }

    public Card getCardByID(String cardId) throws EntityNotFoundException {
        return cardRepository.getCardById(cardId).orElseThrow(() -> new EntityNotFoundException("CardID '" + cardId + "' not found"));
    }

    public Card logIn(String cardId, String pin) throws LoginException {
        Card card;
        try {
            card = getCardByID(cardId);
        } catch (EntityNotFoundException e) {
            throw new LoginException("Invalid card ID / pin");
        }
        if(card.getPin().equals(pin)) {
            return card;
        }
        throw new LoginException("Invalid card ID / pin");
    }

    public String changePin(Card card, String newPin) {
        if (newPin.length() == 4 && stringManipulation.checkIfOnlyDigits(newPin)) {
            card.setPin(newPin);
            try {
                cardRepository.update(card);
            } catch (DatabaseException e) {
                return ActionStatus.FAIL + " " + e.getMessage();
            }
            return ActionStatus.SUCCESS;
        }
        return ActionStatus.FAIL;
    }


}
