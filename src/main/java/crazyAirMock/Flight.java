package crazyAirMock;

import java.util.Date;

public class Flight {

private String origin;
private String destination;
private Date departureDate;
private Date returnDate;
private int passengerCount;


public Flight(int i) {
	this.passengerCount = i;
}


public String getOrigin() {
	return origin;
}


public void setOrigin(String origin) {
	this.origin = origin;
}


public String getDestination() {
	return destination;
}


public void setDestination(String destination) {
	this.destination = destination;
}


public Date getDepartureDate() {
	return departureDate;
}


public void setDepartureDate(Date departureDate) {
	this.departureDate = departureDate;
}


public Date getReturnDate() {
	return returnDate;
}


public void setReturnDate(Date returnDate) {
	this.returnDate = returnDate;
}


public int getPassengerCount() {
	return passengerCount;
}


public void setPassengerCount(int passengerCount) {
	this.passengerCount = passengerCount;
}





}
