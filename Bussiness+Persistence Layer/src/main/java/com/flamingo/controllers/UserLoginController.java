package com.flamingo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flamingo.entities.Passenger;
import com.flamingo.entities.UserLogin;
import com.flamingo.services.PassengerService;
import com.flamingo.services.UserServices;

@RestController
public class UserLoginController {
	@Autowired
	private UserServices userServices;

	@Autowired
	private PassengerService passengerService;


	@GetMapping("/user/{id}")
	public UserLogin findUserById(@PathVariable("id") int id) {
		return userServices.findUserById(id);
	}

	@PostMapping("/user/add/{id}")
	public String add(@RequestBody UserLogin user,@PathVariable("id") int passengerprofileid) {
		  

		userServices.addUser(user);
		
		UserLogin u = userServices.findUserById(user.getId());
		Passenger passenger=passengerService.findPassengersById(passengerprofileid);
		System.out.println(passenger);
		u.setProfileId(passenger);
		userServices.updateUser(u);
		userServices.updateUser(user);
		
		return "new user is added : " + user.getId();

	}

	@PutMapping("/user/update")
	public String update(@RequestBody UserLogin user) {
		if (userServices.findUserById(user.getId()) != null) {
			userServices.updateUser(user);

			return "user updated successfuly :" +user.getId();
		} else {
			return "sorry user not found!";
		}
	}

}
