package com.masai;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.masai.entities.BusDetails;
import  com.masai.entities.Passenger;
import  com.masai.entities.Transaction;
import  com.masai.exception.InvalidDetailsException;
import  com.masai.service.AdminService;
import  com.masai.service.BusDetailServices;
import  com.masai.service.PassengerService;
import  com.masai.service.AdminServiceImpl;
import  com.masai.service.PassengerImpl;
import  com.masai.utility.FileExist;

public class Main {
	
	private static final String BUS_FILE_NAME = "busDetials.ser";

	public static void main(String[] args) {
		
		Map<String,Passenger> passenger= FileExist.passengerFile();
		Map<String,BusDetails> busDetails = FileExist.busDetails();
		Map<Long,Transaction> transactions = FileExist.transaction();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("********************************************");
		System.out.println("* Welcome To, Apna Bus Reservation System **");
		System.out.println("******************************************** \n");
		int preference = 0;
		do {
			System.out.println("********************************************");
			System.out.println("* Please enter your preference 		   *");
			System.out.println("* 1. for Admin Login                   	   *");
			System.out.println("* 2. for Passenger Service                 *");
			System.out.println("* 0. for Exit                  		   *");
			System.out.println("********************************************");
			preference = sc.nextInt();
			switch(preference){
			case 1:
				adminService(sc,busDetails,transactions);
				break;
			case 2:
				passengerService(sc,passenger,transactions, busDetails);
				break;
			default: 
				System.out.println("Please Enter a valid preference");
			}
			
		} while(preference != 0);
		
		
		
	}

	private static void passengerService(Scanner sc,
			Map<String, Passenger> passenger,Map<Long,Transaction> transactions,
			Map<String, BusDetails> busDetails) {
		
		int preference = 0;
		Passenger currentPassenger = null;
		do {
			PassengerService psi = new PassengerImpl();
			System.out.println("***********************************");
			System.out.println("* 1. Sign Up                      *");
			System.out.println("* 2. Login                        *");
			System.out.println("***********************************");
			preference = sc.nextInt();
			try {
				switch(preference){
				case 1:
					currentPassenger = psi.signUp(sc,passenger);
					preference = 0;
					break;
				case 2:
					currentPassenger = psi.signIn(sc,passenger);	
					preference = 0;
					break;
				default: 
					throw new Exception("Please Enter Valid Preference");
				}
				
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
			finally{
				updatePassengerSerFile(passenger);
			}
			
		} while(preference != 0);
		
		passengerServiceAfterLoggedIn(sc,passenger,busDetails, currentPassenger,transactions);
		
		
	}

	private static void passengerServiceAfterLoggedIn(Scanner sc,Map<String,Passenger> passenger,
			Map<String, BusDetails> busDetails, Passenger currentPassenger,  
			Map<Long,Transaction> transactions) {
		
		
		int preference = 0;
		PassengerService ps = new PassengerImpl();
		do {
			System.out.println("****************************************************");
			System.out.println("Hello " + currentPassenger.getUsername() + " please choose a service");
			System.out.println("* 1. Book Ticket                                   *");
			System.out.println("* 2. Add To Wallet                                 *");
			System.out.println("* 3. View Transaction History                      *");
			System.out.println("* 4. Cancel Ticket                                 *");
			System.out.println("* 5. View Wallet Balance                           *");
			System.out.println("* 0. Exit                                          *");
			System.out.println("****************************************************");
			preference = sc.nextInt();
			
			try {
				switch(preference){
				case 1:
					ps.bookTicket(sc,passenger, transactions, busDetails, currentPassenger);
					break;
				case 2:
					ps.addToWallet(sc,passenger,currentPassenger);
					break;
				case 3:
					ps.bookingHistory(currentPassenger.getUsername(),transactions);
					break;
				case 4:
					ps.deleteTicket(sc,transactions);
					break;
				case 5:
					ps.viewWalletBalance(currentPassenger);
				default: 
					throw new Exception("Please Enter Valid Preference");
				}
				
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			finally {
				updatePassengerSerFile(passenger);
				updateTransactionSerFile(transactions);
				updateSerFile(busDetails);
			}
		} while(preference != 0);
		
	}

	private static void updateTransactionSerFile(Map<Long, Transaction> transactions) {
		try {
			ObjectOutputStream oos = 
					new ObjectOutputStream(new FileOutputStream("transaction.ser"));
			oos.writeObject(transactions);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void updatePassengerSerFile(Map<String, Passenger> passenger) {
		try {
			ObjectOutputStream oos = 
					new ObjectOutputStream(new FileOutputStream("passenger.ser"));
			oos.writeObject(passenger);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void adminService(Scanner sc, Map<String, BusDetails> busDetails,
			Map<Long,Transaction> transactions) {
		
		AdminService as = new AdminServiceImpl();
		try {
			as.login(sc);
		} catch(InvalidDetailsException e) {
			System.out.println(e.getMessage());
			return;
		}
		int preference = 0;
		do {
			System.out.println("*********************************************");
			System.out.println("* Please enter your preference.             *");
			System.out.println("* 1. Add Bus Details.                       *");
			System.out.println("* 2. Update Bus Details                     *");
			System.out.println("* 3. Delete Bus Details                     *");
			System.out.println("* 4. View Bus Details.                      *");
			System.out.println("* 5. View Booking By Username of Passenger. *");
			System.out.println("* 6. View Bookings By Bus Number.           *");
			System.out.println("* 0. Exit.                                  *");
			System.out.println("*********************************************");
			preference = sc.nextInt();
			try {
				switch(preference){
				case 1:
					as.addBusDetails(sc,busDetails);
					break;
				case 2:
					as.updateBusDetils(sc,busDetails);	
					System.out.println("*********************************************");
					System.out.println("* Bus Details Successfully Updated          *");
					System.out.println("*********************************************");
					break;
				case 3:	
					as.deleteBusDetails(sc,busDetails);
					System.out.println("*********************************************");
					System.out.println("* Bus Details Successfully Deleted          *");
					System.out.println("*********************************************");
					break;
				case 4:
					as.viewBusDetails(busDetails);
					break;
				case 5:
					as.viewbookingByUserNameOfPassenger(transactions,sc);
					break;
				case 6:
					as.viewbookingsByBusName();
					break;
				case 7:
					as.viewBookingForDateRange();
					break;
				default: 
					throw new Exception("Please Enter Valid Preference");
				}
				
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			finally {
				updateSerFile(busDetails);
			}
		} while(preference != 0);
		
		
	}

	private static void updateSerFile(Map<String, BusDetails> busDetails) {
		try {
			ObjectOutputStream oos = 
					new ObjectOutputStream(new FileOutputStream("busDetials.ser"));
			oos.writeObject(busDetails);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
