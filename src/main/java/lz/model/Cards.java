package lz.model;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private List<Card> cardsList;

    public List<Card> getCardList() {
	if (cardsList == null) {
	    cardsList = new ArrayList<>();
	}
	return cardsList;
    }

    public void setCardList(List<Card> cardsList) {
	this.cardsList = cardsList;
    }
}