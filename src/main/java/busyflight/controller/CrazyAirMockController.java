package busyflight.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crazyair.FlightResponse;

@RestController
public class CrazyAirMockController {

    @RequestMapping("/crazyAirMock")
    public String index() {
	return "Greetings from crazyAir";
    }

    @RequestMapping("/crazyAirMock/flights")
    public FlightResponse flights() {
	final FlightResponse flightResponse = new FlightResponse();
	flightResponse.setDepartureAirportCode("crazyAirMock-origin");
	flightResponse.setPrice(47);
	return flightResponse;
    }
}