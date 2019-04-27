package lz.model;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private List<Card> cardsList;

    public List<Card> getEmployeeList() {
	if (cardsList == null) {
	    cardsList = new ArrayList<>();
	}
	return cardsList;
    }

    public void setEmployeeList(List<Card> cardsList) {
	this.cardsList = cardsList;
    }
}