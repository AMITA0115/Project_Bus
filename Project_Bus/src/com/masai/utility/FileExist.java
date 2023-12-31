package com.masai.utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.masai.entities.BusDetails;
import com.masai.entities.Passenger;
import com.masai.entities.Transaction;

public class FileExist {
	
	public static Map<String,Passenger> passengerFile(){
		
		File f = new File("passenger.ser");
		Map<String,Passenger> pFile = null;
		try {
			if(!f.exists()) {
				f.createNewFile();
				 pFile = new HashMap<>();
				ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(f));
				oos.writeObject(pFile);
			} else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				pFile = (Map<String,Passenger>) ois.readObject();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pFile;	
	}
	
	public static Map<String,BusDetails> busDetails(){
		
		File f = new File("busDetials.ser");
		Map<String,BusDetails> bFile = null;
		try {
			if(!f.exists()) {
				f.createNewFile();
				bFile = new HashMap<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(bFile);
			} else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				bFile = (Map<String,BusDetails>) ois.readObject();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return bFile;
		
	}
	
	public static Map<Long, Transaction> transaction(){
		
		File f = new File("transaction.ser");
		Map<Long, Transaction> tFile = new HashMap<>();
		try {
			if(!f.exists()) {
				f.createNewFile();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(tFile);
			} else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				tFile = (Map<Long, Transaction>) ois.readObject();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return tFile;
	}
	
}
