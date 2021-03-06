package com.flamingo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Component
@Entity
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int profileId;

	@OneToOne(mappedBy = "profileid",cascade = CascadeType.ALL,fetch = FetchType.EAGER)  //updated by charan and akansh
	@JsonIgnore
	private UserLogin user;
	@OneToMany(mappedBy = "profile",fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Payment> cardlist;

	private String fname;
	private String mname;

	private String lname;

	private String address;

	private int phoneNo;
	@Column(unique = true)
	private String emailId;
	@OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<TicketInfo> tkt;
	
	public List<TicketInfo> getTkt() {
		return tkt;
	}

	public void setTkt(List<TicketInfo> tkt) {
		this.tkt = tkt;
	}

	public UserLogin getUser() {
		return user;
	}

	public void setUser(UserLogin user) {
		this.user = user;
	}

	public List<Payment> getCardlist() {
		return cardlist;
	}

	public void setCardlist(List<Payment> cardlist) {
		this.cardlist = cardlist;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	

}
