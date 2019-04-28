package lz.service;

import java.util.Collection;
import java.util.List;

import lz.dao.CardDAO;
import lz.dao.Dao;
import lz.model.Card;

//@Scope(value = "session")
//@Component(value = "todoService")
public class CardService {

    // @Autowired
    private Dao<Card> todoDao;
    private Card todo = new Card();

    private final CardDAO cardDao;

    public CardService(CardDAO cardDao) {
	this.cardDao = cardDao;
    }

    public CardService() {
	this(new CardDAO());
    }

    public void save() {
	todoDao.save(todo);
	todo = new Card();
    }

    public Collection<Card> getAllTodo() {
	return todoDao.getAll();
    }

    public int saveTodo(Card todo) {
	validate(todo);
	return todoDao.save(todo);
    }

    private void validate(Card todo) {
	// Details omitted
    }

    public Card getTodo() {
	return todo;
    }

    public void createCard(Card card, String sessionId) {
	cardDao.addCard(card, sessionId);

    }

    public List<Card> getCards(String sessionId) {
	return cardDao.getCards(sessionId);
    }

    public void deleteCards(String sessionId) {
	cardDao.deleteCards(sessionId);

    }
}