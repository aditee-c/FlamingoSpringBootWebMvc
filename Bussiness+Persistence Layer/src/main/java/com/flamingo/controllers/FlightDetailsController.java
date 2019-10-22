package com.flamingo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flamingo.entities.Flight;
import com.flamingo.entities.FlightDetails;
import com.flamingo.services.FlightDetailsServices;
import com.flamingo.services.FlightServices;

@RestController
public class FlightDetailsController {
	@Autowired
	private FlightDetailsServices flightDetailService;
	@Autowired
	private FlightServices flightService;
	@GetMapping("/flightdetails")
	public List<FlightDetails> getAllFlightDetails() {
		return flightDetailService.findAllFlightDetails();
	}

	@GetMapping("/flightdetails/{id}")
	public FlightDetails getFlightDetail(@PathVariable("id") int flightId) {
		return flightDetailService.findFlightDetailsByflightId(flightId);
	}

	@PostMapping("/flightdetails/add/{id}")
	public String add(@RequestBody FlightDetails flightDetail,@PathVariable("id") int flightId) {
		flightDetailService.addFlightDetails(flightDetail);
		
		FlightDetails f = flightDetailService.findFlightDetailsByflightId(flightDetail.getFlightDetailId());
		Flight flight = flightService.findFlightById(flightId);
		f.setFlight(flight);
		flightDetailService.updateFlightDetails(f);
		return "new flightDetail" + flightDetail.getFlightDetailId() + "is added successful";

	}

	@PutMapping("/flightdetails/update/{id}")
	public String update(@RequestBody FlightDetails flightDetail,@PathVariable("id") int flightId) {
		if (flightDetailService.findFlightDetailsByflightId(flightDetail.getFlightDetailId()) != null) {
			FlightDetails f = flightDetailService.findFlightDetailsByflightId(flightDetail.getFlightDetailId());
			Flight flight = flightService.findFlightById(flightId);
			f.setFlight(flight);
			flightDetailService.updateFlightDetails(f);
			flightDetailService.updateFlightDetails(flightDetail);
			return "new flightDetail" + flightDetail.getFlightDetailId() + "is updated successful";
		} else {
			return "sorry!Flight not found";
		}
	}

	@DeleteMapping("/flightdetails/delete")
	public String delete(@RequestBody FlightDetails flightDetail) {
		if (flightDetailService.findFlightDetailsByflightId(flightDetail.getFlightDetailId()) != null) {
			flightDetailService.removeFlightDetails(flightDetail);
			return "new flightDetail" + flightDetail.getFlightDetailId() + "is deleted successful";
		} else {
			return "sorry!Flight not found";
		}
	}

}
