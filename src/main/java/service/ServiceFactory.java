package service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import crazyair.AppProperties;
import crazyair.FlightProviderClient;
import crazyair.client.CrazyAirClient;
import thoughjet.client.ThoughJetClient;

@Service
public class ServiceFactory {

    private final AppProperties appProperties;

    public ServiceFactory(AppProperties appProperties) {
	this.appProperties = appProperties;
    }

    public FlightService createFlightService() {
	return new FlightService(createFlightProviders());
    }

    private Set<FlightProviderClient> createFlightProviders() {
	final Set<FlightProviderClient> providers = new HashSet<>();
	providers.add(new CrazyAirClient(appProperties.getCrazyAirUrl()));
	providers.add(new ThoughJetClient(appProperties.getThoughJetUrl()));
	return providers;
    }

}
