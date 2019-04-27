package lz.model;

import java.util.Set;

public class CardsResponse {
    private final Set<Card> cards;

    public CardsResponse(Set<Card> cards) {
	this.cards = cards;
    }

    public Set<Card> getCards() {
	return cards;
    }

}