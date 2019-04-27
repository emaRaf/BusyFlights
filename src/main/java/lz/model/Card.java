package lz.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@Entity
public class Card {
    public Card() {

    }

    public Card(String bankName, String cardNumber, String expiryDate) {
	this.bankName = bankName;
	this.cardNumber = cardNumber;
	this.expiryDate = expiryDate;
    }

    // @NotBlank(message = "Name is mandatory")
    @NotBlank(message = "username can't empty!")
    @NotNull(message = "Name is mandatory")
    @Size(min = 2, max = 30)
    private String bankName;

    // @Id
    @NotBlank(message = "username can't empty!")
    @NotNull(message = "cardNumber is mandatory")
    @Size(min = 2, max = 30)
    private String cardNumber;

    @NotBlank(message = "username can't empty!")
    // @NotBlank(message = "Date is mandatory")
    @NotNull(message = "Date is mandatory")
    @Size(min = 2, max = 30)
    private String expiryDate;

    public String getBankName() {
	return bankName;
    }

    public void setBankName(String bankName) {
	this.bankName = bankName;
    }

    public String getCardNumber() {
	return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
	this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
	return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
	this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
	return "Card [bankName=" + bankName + ", cardNumber=" + cardNumber + ", expiryDate=" + expiryDate + "]";
    }

}
