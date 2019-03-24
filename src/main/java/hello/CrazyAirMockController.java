package hello;

import org.springframework.web.bind.annotation.RestController;

import crazyAirMock.Flight;
import crazyAirMock.FlightsResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
//@RestController
public class CrazyAirMockController {

    @RequestMapping("/crazyAirMock")
    public String index() {
        return "Greetings from crazyAir";
    }

    @RequestMapping("/crazyAirMock/flights")
     public FlightsResponse flights() {
    	FlightsResponse flightsResponse=new FlightsResponse();
    	List<Flight> flights=new ArrayList<>();
    	flights.add(new Flight(1));
    	flightsResponse.setFlights(flights);
        return flightsResponse;
    }
}