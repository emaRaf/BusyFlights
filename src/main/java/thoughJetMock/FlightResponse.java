package thoughJetMock;

import java.util.List;

public class FlightResponse {

	/*
	carrier	Name of the Airline
	basePrice	Price without tax(doesn't include discount)
	tax	Tax which needs to be charged along with the price
	discount	Discount which needs to be applied on the price(in percentage)
	departureAirportName	3 letter IATA code(eg. LHR, AMS)
	arrivalAirportName	3 letter IATA code(eg. LHR, AMS)
	outboundDateTime	ISO_INSTANT format
	inboundDateTime	ISO_INSTANT format
	*/
	private List<Flight> flights;

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
	
	
}
