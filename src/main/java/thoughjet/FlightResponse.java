package thoughjet;

import java.util.Date;

public class FlightResponse {

    private String carrier;
    private double basePrice;
    private double tax;
    private double discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private Date outboundDateTime;
    private Date inboundDateTime;

    public double getTax() {
	return tax;
    }

    public void setTax(double tax) {
	this.tax = tax;
    }

    public String getCarrier() {
	return carrier;
    }

    public double getBasePrice() {
	return basePrice;
    }

    public double getDiscount() {
	return discount;
    }

    public String getDepartureAirportName() {
	return departureAirportName;
    }

    public String getArrivalAirportName() {
	return arrivalAirportName;
    }

    public Date getOutboundDateTime() {
	return outboundDateTime;
    }

    public Date getInboundDateTime() {
	return inboundDateTime;
    }

    public void setCarrier(String carrier) {
	this.carrier = carrier;
    }

    public void setBasePrice(double basePrice) {
	this.basePrice = basePrice;
    }

    public void setDiscount(double discount) {
	this.discount = discount;
    }

    public void setDepartureAirportName(String departureAirportName) {
	this.departureAirportName = departureAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
	this.arrivalAirportName = arrivalAirportName;
    }

    public void setOutboundDateTime(Date outboundDateTime) {
	this.outboundDateTime = outboundDateTime;
    }

    public void setInboundDateTime(Date inboundDateTime) {
	this.inboundDateTime = inboundDateTime;
    }
}
