package lz.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import lz.exception.CardException;
import lz.model.Card;

@Repository
public class CardDAO {
    private final static Logger LOG = Logger.getLogger(CardDAO.class.getName());

    private static List<Card> cards = new ArrayList<>();

    /*
     * static { cards.add(new Card("bank1", "num1", "exp1")); cards.add(new
     * Card("bank2", "num2", "exp2")); cards.add(new Card("bank3", "num3", "exp3"));
     * }
     */

    public List<Card> getCards() {
	return cards;
    }

    public void addCard(Card card) {
	if (cards.contains(card)) {
	    throw new CardException("card already existing");
	} else {
	    cards.add(card);
	    LOG.info("added new card from " + card.getBankName() + ", current total: " + cards.size());
	}
    }
}