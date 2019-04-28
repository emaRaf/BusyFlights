package lz.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import lz.exception.CardException;
import lz.model.Card;

@Repository
public class CardDAO {
    private final static Logger LOG = Logger.getLogger(CardDAO.class.getName());

    private Map<String, List<Card>> cards;
    private List<Card> allCards;

    public CardDAO() {
	cards = new HashMap<>();
	allCards = new ArrayList<>();
    }

    public Map<String, List<Card>> getCards() {
	return cards;
    }

    public void setCards(Map<String, List<Card>> cards) {
	this.cards = cards;
    }

    public List<Card> getAllCards() {
	return allCards;
    }

    public void setAllCards(List<Card> allCards) {
	this.allCards = allCards;
    }

    public List<Card> getCards(String sessionId) {
	final List<Card> cardsBySession = cards.get(sessionId);
	return cardsBySession != null ? cardsBySession : new ArrayList<>();
//	return allCards;
    }

    public void addCard(Card card, String sessionId) {
	List<Card> cardsBySessionId = cards.get(sessionId);

	if (cardsBySessionId == null) {
	    LOG.info("creating new card list for session id: " + sessionId);

	    cardsBySessionId = new ArrayList<>();
	    cards.put(sessionId, cardsBySessionId);
	}

	if (cardsBySessionId.contains(card)) {
	    throw new CardException("card already existing");
	} else {
	    cardsBySessionId.add(card);
	    LOG.info("added new card from " + card.getBankName() + ", current total: " + cardsBySessionId.size());
	}

	cards.replace(sessionId, cardsBySessionId);

	allCards.add(card);
    }

    public void deleteCards(String sessionId) {
	cards.remove(sessionId);
    }
}