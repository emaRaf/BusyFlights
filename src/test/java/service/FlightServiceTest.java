package service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import busyflight.Flight;
import crazyair.FlightProviderClient;

public class FlightServiceTest {

    FlightService service;

    @Mock
    FlightProviderClient flightProviderMock1;

    @Mock
    FlightProviderClient flightProviderMock2;

    @Mock
    FlightProviderClient flightProviderMock3;

    @Mock
    FlightProviderClient flightProviderMock4;

    @Test
    public void testCollectFlights() {
	MockitoAnnotations.initMocks(this);

	when(flightProviderMock1.getFlight()).thenReturn(new Flight(null, null, 5, null, null, null, null));
	when(flightProviderMock2.getFlight()).thenReturn(new Flight(null, null, 2.8, null, null, null, null));
	when(flightProviderMock3.getFlight()).thenReturn(new Flight(null, null, 2.78, null, null, null, null));
	when(flightProviderMock4.getFlight()).thenReturn(new Flight(null, null, 2.9, null, null, null, null));

	final FlightService service = new FlightService(new HashSet<>(
		Arrays.asList(flightProviderMock2, flightProviderMock4, flightProviderMock1, flightProviderMock3)));
	final List<Flight> flights = new ArrayList<>(service.collectFlights());

	assertThat(flights.get(0).getFare(), equalTo(2.78));
	assertThat(flights.get(1).getFare(), equalTo(2.8));
	assertThat(flights.get(2).getFare(), equalTo(2.9));
	assertThat(flights.get(3).getFare(), equalTo(5));
    }
}