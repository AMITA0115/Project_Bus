package com.masai.service;


import java.util.*;

import com.masai.entities.BusDetails;
import com.masai.entities.Passenger;
import com.masai.entities.Transaction;
import com.masai.exception.InvalidDetailsException;


public interface PassengerService {
	
	public Passenger signUp(Scanner sc,Map<String,Passenger> passenger) throws InvalidDetailsException ;
	
	public Passenger signIn(Scanner sc,Map<String,Passenger> passenger)throws InvalidDetailsException ;

	public void bookTicket(Scanner sc ,Map<String,Passenger> passenger,
			Map<Long,Transaction> transactions,
			Map<String, BusDetails> busDetails, Passenger currentPassenger);
	
	public void deleteTicket(Scanner sc,Map<Long,Transaction> transactions);
	
	public void bookingHistory(String userName,Map<Long,Transaction> transactions);
	
	public void deleteAccount();

	public void addToWallet(Scanner sc, Map<String, Passenger> passenger, Passenger currentPassenger);

	public void viewWalletBalance(Passenger currentPassenger);
	
		
}
