package com.flamingo.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flamingo.dao.FlightDao;
import com.flamingo.entities.Flight;
import com.flamingo.services.FlightServices;
import com.flamingo.validators.FlightValidator;
@RestController
public class FlightController {
	
	
	@Autowired
	FlightDao flightdao;
	@Autowired
	private FlightServices flightService;

	@RequestMapping("/flight/list")
	public List<Flight> getAllFlights() {
		return flightService.findAllFlights();
	}

	@RequestMapping("/flight/{id}")
	public Flight flightById(@PathVariable("id") int flightid) {
		return flightService.findFlightById(flightid);
	}

	@PostMapping("/flight/add")
	public String addFlight(@RequestParam("id") int flightId, @RequestParam("name") String flightName,
			@RequestParam("from") String fromLocation, @RequestParam("to") String toLocation,
			@RequestParam("depdate") String departureDate, @RequestParam("arrivaldate") String arrivalDate,
			@RequestParam("departuretime") String departureTime, @RequestParam("arrivaltime") String arrivalTime,
			@RequestParam("duration") String duration, @RequestParam("totalseats") int totalSeats)
	{
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:mm");
		LocalDate date = LocalDate.parse(departureDate, dtf);
		LocalDate arrivaldate = LocalDate.parse(arrivalDate, dtf);
		LocalTime arrivaltime = LocalTime.parse(arrivalTime,dt);
		LocalTime departuretime = LocalTime.parse(departureTime,dt);
		Flight flight = new Flight(flightId, flightName, fromLocation, toLocation, date, arrivaldate,
				departuretime, arrivaltime, duration, totalSeats);
		flightService.addFlight(flight);
		return "flight successfully added";
	}

	@PutMapping("/flight/update")
	public String updateFlight(@RequestBody Flight flight) {
		if (flightService.findFlightById(flight.getFlightId()) != null) {
			flightService.updateFlight(flight);
			return "Flight is Updated Successfully";
		} else {
			return "sorry flight not found";
		}
	}

	@DeleteMapping("/flight/delete")
	public String deleteFlight(@RequestBody Flight flight) {
		if (flightService.findFlightById(flight.getFlightId()) != null) {
			flightService.removeFlight(flight);
			return "Flight is deleted Successfully";
		} else {
			return "sorry flight not found";
		}
	}

	@PostMapping("/flight/bylocation")
	public List<Flight> flightBylocation(@RequestParam("from") String from, @RequestParam("to") String to) {
		System.out.println(flightService.findFlightByLocation(from,to));
		return flightService.findFlightByLocation(from,to);
	}

	@PostMapping("/flight/bydate")
	public List<Flight> flightBydate(@RequestParam("bydate") String dateStr){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy");
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(dateStr, dtf);
		return flightService.findFlightByDate(date);
	}

	/*
	 * @GetMapping("/flight/bycount") public long countOfSeats() { return
	 * flightService.getCountOfSeats(); }
	 */

}
