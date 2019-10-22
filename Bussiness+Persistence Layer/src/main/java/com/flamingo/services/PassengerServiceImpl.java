package com.flamingo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flamingo.dao.PassengerDao;
import com.flamingo.dao.PassengerDaoImpl;
import com.flamingo.dao.UserDao;
import com.flamingo.entities.Passenger;
import com.flamingo.entities.UserLogin;

@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {
	@Autowired
	private PassengerDao passengerDao;
	//private UserDao userDao;
	
	

	public List<Passenger> SeeAllPassenger() {
		// TODO Auto-generated method stub
		
		return passengerDao.getAll();
	}

	public void addPassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		passengerDao.insert(passenger);
	}

	public void removePassenger(Passenger passenger) { 
		// TODO Auto-generated method stub
		 passengerDao.delete(passenger);

	}

	public void updatePassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		passengerDao.update(passenger);

	}

	@Override
	public Passenger findPassengersById(int id) {
		// TODO Auto-generated method stub
		return passengerDao.getById(id);
	}

	@Override
	public void addUser(UserLogin userLogin) {
		// TODO Auto-generated method stub
		
		passengerDao.addUser(userLogin);
	}

}