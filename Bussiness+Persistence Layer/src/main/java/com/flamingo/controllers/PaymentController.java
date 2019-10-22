package com.flamingo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flamingo.entities.Passenger;
import com.flamingo.entities.Payment;
import com.flamingo.services.PassengerService;
import com.flamingo.services.PaymentService;

/**
 * @author akansh_sai
 * @creation_ date 12th oct 2019 9:00pm
 * @modification_ date 12th oct 2019 9:00pm
 * @version 1.0
 * @copyright Zensar technologies. all rights reserved.
 * @description controller implementation by payment
 *
 */
@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private PassengerService passengerService;

	@PostMapping("/payment/add/{id}")
	public String addPayment(@RequestBody Payment payment, @PathVariable("id") int profileid) {
		paymentService.addCard(payment);
		Payment p = paymentService.findPaymentById(payment.getId());
		Passenger passenger = passengerService.findPassengersById(profileid);
		p.setProfile(passenger);
		paymentService.updateCard(p);
		return "card added of user";
	}

	@PutMapping("/payment/update/{id}")
	public String updatePayment(@RequestBody Payment payment, @PathVariable("id") int profileid) {
		Passenger passenger = passengerService.findPassengersById(profileid);
		Payment p = paymentService.findPaymentById(payment.getId());
		if (p != null) {
			p.setProfile(passenger);
			paymentService.updateCard(p);
			paymentService.updateCard(payment);
			return "payment is updated ";
		} else {
			return "Payment Not found";
		}

	}

	@DeleteMapping("/payement/delete")
	public String delete(@RequestBody Payment payment) {
		if (paymentService.findPaymentById(payment.getId()) != null) {
			paymentService.removeCard(payment);
			return "payment is deleted ";// +payment.getProductId();
		} else {
			return "payment not found";
		}
	}
}
