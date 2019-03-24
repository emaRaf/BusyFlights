package busyflight;

import java.util.Date;

public class Flight implements Comparable<Flight> {

    private String airline;
    private String supplier;
    private double fare;
    private String departureAirportCode;
    private String destinationAirportCode;
    private Date departureDate;
    private Date arrivalDate;

    public Flight(String airline, String supplier, double fare, String departureAirportCode,
	    String destinationAirportCode, Date departureDate, Date arrivalDate) {
	this.airline = airline;
	this.supplier = supplier;
	this.fare = fare;
	this.departureAirportCode = departureAirportCode;
	this.destinationAirportCode = destinationAirportCode;
	this.departureDate = departureDate;
	this.arrivalDate = arrivalDate;
    }

    public Flight() {
    }

    public String getAirline() {
	return airline;
    }

    public String getSupplier() {
	return supplier;
    }

    public double getFare() {
	return fare;
    }

    public String getDepartureAirportCode() {
	return departureAirportCode;
    }

    public String getDestinationAirportCode() {
	return destinationAirportCode;
    }

    public Date getDepartureDate() {
	return departureDate;
    }

    public Date getArrivalDate() {
	return arrivalDate;
    }

    public void setAirline(String airline) {
	this.airline = airline;
    }

    public void setSupplier(String supplier) {
	this.supplier = supplier;
    }

    public void setFare(double fare) {
	this.fare = fare;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
	this.departureAirportCode = departureAirportCode;
    }

    public void setDestinationAirportCode(String destinationAirportCode) {
	this.destinationAirportCode = destinationAirportCode;
    }

    public void setDepartureDate(Date departureDate) {
	this.departureDate = departureDate;
    }

    public void setArrivalDate(Date arrivalDate) {
	this.arrivalDate = arrivalDate;
    }

    @Override
    public int compareTo(Flight flight) {
	return Double.compare(this.getFare(), flight.getFare());
    }
}
