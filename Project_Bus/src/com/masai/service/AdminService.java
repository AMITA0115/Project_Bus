package com.masai.service;



import java.util.Map;
import java.util.Scanner;

import com.masai.entities.BusDetails;
import com.masai.entities.Transaction;
import com.masai.exception.DuplicateBusNumberException;
import com.masai.exception.InvalidDetailsException;

public interface AdminService {
	void login(Scanner sc) throws InvalidDetailsException;
	void addBusDetails(Scanner sc,Map<String,BusDetails> busDetails) throws DuplicateBusNumberException;
	void updateBusDetils(Scanner sc,Map<String,BusDetails> busDetails)throws InvalidDetailsException;
	void deleteBusDetails(Scanner sc,Map<String,BusDetails> busDetails)throws InvalidDetailsException;
	void viewBusDetails(Map<String,BusDetails> busDetails);
	void viewBookingForDateRange();
	void viewbookingsByBusName();
	void viewbookingByUserNameOfPassenger(Map<Long,Transaction> transactions,Scanner sc);	
}


