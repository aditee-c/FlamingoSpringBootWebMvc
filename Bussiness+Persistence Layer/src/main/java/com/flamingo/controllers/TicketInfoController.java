package com.flamingo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author risha ansari
 * @creation_date 12 oct 2019 03.40pm
 * @modification_date 12 oct 2019 03.40pm
 * @version 1.0
 * @copyright zensar technologies.all rights reserved
 * @description It is a restful web service to access product entity
 *
 */

import org.springframework.web.bind.annotation.RestController;

import com.flamingo.dao.FlightDao;
import com.flamingo.dao.PassengerDao;
import com.flamingo.dao.TicketInfoDao;
import com.flamingo.entities.Flight;
import com.flamingo.entities.FlightDetails;
import com.flamingo.entities.Passenger;
import com.flamingo.entities.TicketInfo;
import com.flamingo.services.FlightDetailsServices;
import com.flamingo.services.FlightServices;
import com.flamingo.services.PassengerService;
import com.flamingo.services.TicketInfoServices;

@RestController
public class TicketInfoController {
	@Autowired
	private TicketInfoServices ticketInfoService;
	@Autowired
	private FlightDetailsServices flightService;
	@Autowired
	private PassengerService passengerService;
	@Autowired
	private PassengerDao passengerDao ;
	@Autowired
	private TicketInfoDao ticketinfodao;
	@Autowired
	private FlightDao flightdao;
	
	@GetMapping("/ticket")
	public List<TicketInfo> getAllTickets() {

		return ticketInfoService.findAllTicketInfos();

	}

	@GetMapping("/ticket/{id}")
	public TicketInfo getTicket(@PathVariable("id") int ticketId) {
		return ticketInfoService.findTicketInfoById(ticketId);

	}

	@PostMapping("/ticket/add/{fid}/{pid}")
	public String addTicket(@RequestBody TicketInfo ticketinfo,@PathVariable("fid") int flightid,@PathVariable("pid") int profileid) {
		ticketInfoService.add(ticketinfo);
		TicketInfo ticket = ticketInfoService.findTicketInfoById(ticketinfo.getTicketId());
		FlightDetails flight = flightService.findFlightDetailsByflightId(flightid);
		Passenger passenger = passengerService.findPassengersById(profileid);
		ticket.setProfile(passenger);
		ticket.setFlightdetail(flight);
		ticketInfoService.update(ticket);
		return "new ticket is added" + ticketinfo.getTicketId();
	}

	@PutMapping("ticket/update/{fid}/{pid}")
	public String update(@RequestBody TicketInfo ticketInfo,@PathVariable("fid") int flightid,@PathVariable("pid") int profileid) {
		TicketInfo ticket = ticketInfoService.findTicketInfoById(ticketInfo.getTicketId());
		FlightDetails flight = flightService.findFlightDetailsByflightId(flightid);
		Passenger passenger = passengerService.findPassengersById(profileid);

		if (ticket != null) {
			ticket.setProfile(passenger);
			ticket.setFlightdetail(flight);
			ticketInfoService.update(ticket);
			ticketInfoService.update(ticketInfo);
			return "ticket is updated successfully" + ticketInfo.getTicketId();

		} else {
			return "sorry ticket not found";
		}

	}

	@DeleteMapping("ticket/delete")
	public String delete(@RequestBody TicketInfo ticketInfo) {
		if (ticketInfoService.findTicketInfoById(ticketInfo.getTicketId()) != null) {
			ticketInfoService.remove(ticketInfo);
			return "ticket is deleted successfully";

		} else {
			return "sorry ticket not found";
		}

	}
	
		
	}
