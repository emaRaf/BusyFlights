package thoughjet;

import java.util.Date;

public class FlightRequest {

    private String from;
    private String to;
    private Date outboundDate;
    private Date inboundDate;
    private int numberOfAdults;

    public String getFrom() {
	return from;
    }

    public void setFrom(String from) {
	this.from = from;
    }

    public String getTo() {
	return to;
    }

    public void setTo(String to) {
	this.to = to;
    }

    public Date getOutboundDate() {
	return outboundDate;
    }

    public void setOutboundDate(Date outboundDate) {
	this.outboundDate = outboundDate;
    }

    public Date getInboundDate() {
	return inboundDate;
    }

    public void setInboundDate(Date inboundDate) {
	this.inboundDate = inboundDate;
    }

    public int getNumberOfAdults() {
	return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
	this.numberOfAdults = numberOfAdults;
    }

}
