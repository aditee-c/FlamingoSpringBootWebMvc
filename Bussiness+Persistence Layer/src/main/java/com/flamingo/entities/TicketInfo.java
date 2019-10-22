package com.flamingo.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Component
@Entity
public class TicketInfo {
	@Id
	private int ticketId;
	private int seatNo;
	private String status;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profileid")
	@JsonIgnore
	private Passenger profile;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "flightid")
	@JsonIgnore
	private FlightDetails flightdetail;

	public FlightDetails getFlightdetail() {
		return flightdetail;
	}

	public void setFlightdetail(FlightDetails flightdetail) {
		this.flightdetail = flightdetail;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Passenger getProfile() {
		return profile;
	}

	public void setProfile(Passenger profile) {
		this.profile = profile;
	}

	
}
