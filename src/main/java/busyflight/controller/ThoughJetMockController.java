package busyflight.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import thoughjet.FlightResponse;

@RestController
public class ThoughJetMockController {

    @RequestMapping("/thoughJetMock")
    public String index() {
	return "Greetings from thoughJet";
    }

    @RequestMapping("/thoughJetMock/flights")
    public FlightResponse flights() {
	final FlightResponse flightsResponse = new FlightResponse();
	flightsResponse.setArrivalAirportName("thoughJetMock-arrival");
	flightsResponse.setBasePrice(45);
	return flightsResponse;
    }

}