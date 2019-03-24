package crazyair.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import busyflight.Flight;
import crazyair.FlightProviderClient;
import crazyair.FlightResponse;

public class CrazyAirClient implements FlightProviderClient {

    private final String endpointUrl;

    public CrazyAirClient(String endpointUrl) {
	this.endpointUrl = endpointUrl;
    }

    @Override
    public Flight getFlight() {
	final ObjectMapper mapper = new ObjectMapper();

	try {
	    final FlightResponse response = mapper.readValue(getJson(), FlightResponse.class);
	    return createFlight(response);
	} catch (final Exception e) {
	    e.printStackTrace();
	    throw new RuntimeException();
	}
    }

    private Flight createFlight(FlightResponse response) {
	final Flight flight = new Flight();
	flight.setAirline(response.getAirline());
	flight.setArrivalDate(response.getArrivalDate());
	flight.setDepartureAirportCode(response.getDepartureAirportCode());
	flight.setDepartureDate(response.getArrivalDate());
	flight.setDestinationAirportCode(response.getDestinationAirportCode());
	flight.setFare(response.getPrice());
	flight.setSupplier("Crazy Air");
	return flight;
    }

    private String getJson() throws Exception {

	final URL obj = new URL(endpointUrl + "/flights");
	System.out.println("\nSending 'GET' request to URL : " + endpointUrl + "/flights");

	final HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	final int responseCode = con.getResponseCode();

	System.out.println("Response Code : " + responseCode);

	final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	String inputLine;
	final StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
	    response.append(inputLine);
	}
	in.close();

	return response.toString();
    }

}
