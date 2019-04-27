package lz.model;

public class Result {
    private String message;
    private Card card;

    public Result(String message, Card card) {
	this.message = message;
	this.card = card;
    }

    public Result() {
	// TODO Auto-generated constructor stub
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public Card getCard() {
	return card;
    }

    public void setCard(Card card) {
	this.card = card;
    }

    public void setResult(Card card) {
	this.card = card;

    }

}
