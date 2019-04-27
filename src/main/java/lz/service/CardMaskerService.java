package lz.service;

import lz.model.Card;

public class CardMaskerService {

    private static final String MASK = "xxxx-xxxx-xxxx";

    public Card maskCard(Card card) {
	return new Card(card.getBankName(), maskCardNumber(card.getCardNumber()), card.getExpiryDate());
    }

    private String maskCardNumber(String cardNumber) {
	return cardNumber.substring(0, 5) + MASK;
    }
}
