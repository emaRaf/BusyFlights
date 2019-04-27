package busyflight.controller;

import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import busyflight.Flight;
import service.ServiceFactory;

@RestController
public class BusyFlightController {

    @Autowired
    private ServiceFactory serviceFactory;

    @RequestMapping("/busyFlights")
    public TreeSet<Flight> flights() {

	return serviceFactory.createFlightService().collectFlights();

    }
}