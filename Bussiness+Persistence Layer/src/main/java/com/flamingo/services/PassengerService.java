package com.flamingo.services;

import java.util.List;

import com.flamingo.entities.Passenger;
import com.flamingo.entities.UserLogin;

public interface PassengerService {

	List<Passenger> SeeAllPassenger();  //admin
	

	void addPassenger(Passenger passenger); //passenger
	
	void removePassenger(Passenger passenger); //admin
	
	void updatePassenger(Passenger passenger);  //passenger
			

	Passenger findPassengersById(int id);
	 void addUser(UserLogin userLogin) ; 
	
}