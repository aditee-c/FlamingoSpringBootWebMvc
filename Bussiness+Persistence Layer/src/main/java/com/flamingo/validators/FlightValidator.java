package com.flamingo.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.flamingo.entities.FlightDetails;

public class FlightValidator implements Validator{

	@Override
	public boolean supports(Class aclass) {
		// TODO Auto-generated method stub
		return aclass.equals(FlightDetails.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
	FlightDetails fd = (FlightDetails)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"flightName","error.invalid.flightName","Flight Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"from","error.invalid.fromLocation","From Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"to","error.invalid.toLocation","Dest Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"departTime","error.invalid.departTime","Departure Time Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"arrivalTime","error.invalid.arrivalTime","Arrival Time Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"totalSeats","error.invalid.totalSeats","Total Seats Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"departDate","error.invalid.departDate","Departure Date Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"arrivalDate","error.invalid.arrivalDate","Arrival Date Required");
	
	}

}
