package service;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import busyflight.Flight;
import crazyair.FlightProviderClient;

public class FlightService {

    Set<FlightProviderClient> flightProviders;

    public FlightService(Set<FlightProviderClient> flightProviders) {
	this.flightProviders = flightProviders;
    }

    public TreeSet<Flight> collectFlights() {
	return new TreeSet<>(
		flightProviders.stream().map(provider -> provider.getFlight()).collect(Collectors.toList()));
    }
}
