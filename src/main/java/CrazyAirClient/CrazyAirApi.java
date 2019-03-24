package CrazyAirClient;

import service.factory.Flight;
import service.factory.FlightProviderApi;

public class CrazyAirApi implements FlightProviderApi {

	@Override
	public Flight getFlight() {
		// TODO Auto-generated method stub
		return new Flight(2);
	}

}
