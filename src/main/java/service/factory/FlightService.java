package service.factory;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FlightService {

	Set<FlightProviderApi> flightProviders;
	
	public FlightService(Set<FlightProviderApi> flightProviders) {
		this.flightProviders = flightProviders;
	}

	public Set<Flight> collectFlights() {
		return flightProviders.stream().map(provider->provider.getFlight()).collect(Collectors.toSet());
	}
}
