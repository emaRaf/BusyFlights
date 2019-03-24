package service.factory;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import CrazyAirClient.CrazyAirApi;
import thoughJetClient.ThoughJetApi;

@Service
public class ServiceFactory {

 	
	
public FlightService createFlightService() {
	return new FlightService(createFlightProviders());
}

private Set<FlightProviderApi> createFlightProviders() {
	Set<FlightProviderApi> providers=new HashSet<>();
	providers.add(new CrazyAirApi());
	providers.add(new ThoughJetApi());
	return providers;
}
	
}
