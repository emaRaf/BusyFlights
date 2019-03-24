package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;

import service.factory.Flight;
import service.factory.ServiceFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class BusyFlightController{
	
	   @Autowired
	private ServiceFactory serviceFactory;
	
	
	 


    @RequestMapping("/busyFlights")
    public String index() {
        return "Greetings from BusyFlightController";
    }
    
    
    @RequestMapping("/busyFlights/flights")
    public Set<Flight> flights() {
    	
    	return serviceFactory.createFlightService().collectFlights();
    	
    	
  
    }

}