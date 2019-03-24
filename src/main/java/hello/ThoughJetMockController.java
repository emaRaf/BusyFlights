package hello;

import org.springframework.web.bind.annotation.RestController;

import thoughJetMock.Flight;
import thoughJetMock.FlightResponse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
public class ThoughJetMockController {

    @RequestMapping("/thoughJetMock")
    public String index() {
        return "Greetings from thoughJet";
    }

    @RequestMapping("/thoughJetMock/flights")
    public FlightResponse flights() {
   	FlightResponse flightsResponse=new FlightResponse();
   	List<Flight> flights=new ArrayList<>();
   	flights.add(new Flight(1));
   	flightsResponse.setFlights(flights);
       return flightsResponse;
   }
    
}