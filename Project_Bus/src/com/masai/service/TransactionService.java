package com.masai.service;



import java.util.List;

import com.masai.entities.Passenger;

public interface TransactionService {
	
	List<Passenger> passengerTransaction();
	
	List<Passenger> passengerAllTransaction();
}

