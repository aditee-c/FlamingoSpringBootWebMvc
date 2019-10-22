package com.flamingo.dao;

import java.util.List;

import com.flamingo.entities.Passenger;
import com.flamingo.entities.UserLogin;

public interface PassengerDao {
	List<Passenger> getAll();

	Passenger getById(int profileId);

	void insert(Passenger passenger);

	void update(Passenger passenger);

	void delete(Passenger passenger);
	
	public void addUser(UserLogin userLogin);

}
