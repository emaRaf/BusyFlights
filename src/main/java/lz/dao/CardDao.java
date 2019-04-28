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
public class CardDao {
    private final static Logger LOG = Logger.getLogger(CardDao.class.getName());

    private final Map<String, List<Card>> cards;

    public CardDao() {
	cards = new HashMap<>();
    }

    public List<Card> getCards(String sessionId) {
	final List<Card> cardsBySession = cards.get(sessionId);
	return cardsBySession != null ? cardsBySession : new ArrayList<>();
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
    }

    public void deleteCards(String sessionId) {
	cards.remove(sessionId);
    }
}