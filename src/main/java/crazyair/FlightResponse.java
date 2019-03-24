package crazyair;

import java.util.Date;

public class FlightResponse {

    private String airline;
    private String cabinclass;
    private String departureAirportCode;
    private String destinationAirportCode;
    private Date departureDate;
    private Date arrivalDate;
    private int price;

    public String getAirline() {
	return airline;
    }

    public void setAirline(String airline) {
	this.airline = airline;
    }

    public String getCabinclass() {
	return cabinclass;
    }

    public void setCabinclass(String cabinclass) {
	this.cabinclass = cabinclass;
    }

    public String getDepartureAirportCode() {
	return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
	this.departureAirportCode = departureAirportCode;
    }

    public String getDestinationAirportCode() {
	return destinationAirportCode;
    }

    public void setDestinationAirportCode(String destinationAirportCode) {
	this.destinationAirportCode = destinationAirportCode;
    }

    public Date getDepartureDate() {
	return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
	this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
	return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
	this.arrivalDate = arrivalDate;
    }

    public int getPrice() {
	return price;
    }

    public void setPrice(int price) {
	this.price = price;
    }

}
