package com.flamingo.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.flamingo.entities.FlightDetails;


@Repository
public class FlightDetailsDaoImpl implements FlightDetailsDao {
	@Autowired
	private HibernateTemplate hibernateTemplate; 

	@Override
	public List<FlightDetails> getAll() {
		// TODO Auto-generated method stub
		//"select flightDetailId as FlightDetainId,price as Price,availableSeats as availableSeats,departureDateTime as DepartureTime,flight as flightId from FlightDetails");
		//String sql="select d.flightDetailId,d.price,f.flightName from FlightDetails d INNER JOIN Flight f where d.flightId = flightId.f ";
		//return (List<FlightDetails>) hibernateTemplate.execute();
		
		return (List<FlightDetails>) hibernateTemplate.find("select flightDetailId,price,availableSeats,departureDateTime,flight from FlightDetails");
	}

	@Override
	public void insert(FlightDetails flightdetails) {
		// TODO Auto-generated method stub
              hibernateTemplate.save(flightdetails);
	}

	@Override
	public void update(FlightDetails flightdetails) {
		// TODO Auto-generated method stub
            hibernateTemplate.update(flightdetails);
	}

	@Override
	public void delete(FlightDetails flightdetails) {
		// TODO Auto-generated method stub
            hibernateTemplate.delete(flightdetails);
	}

	@Override
	public FlightDetails getByflightId(int flightId) {
		// TODO Auto-generated method stub
		
		return hibernateTemplate.get(FlightDetails.class, flightId);
	}

}
