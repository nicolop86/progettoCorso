package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import booking.Booking;

public class DAOBooking implements DAOInterface<Integer, Booking> {

	private TreeMap<Integer, Booking> bookingMap;

	public DAOBooking() {
		bookingMap = new TreeMap<>();
	}

	public DAOBooking(ArrayList<Booking> bookingList) {
		for(Booking b : bookingList) {
			bookingMap.put(b.getID(), b);
		}
	}

	@Override
	public ArrayList<Booking> listAll() {
		ArrayList<Booking> allBooking = new ArrayList<Booking>();
		for (Iterator <Integer> it = bookingMap.keySet().iterator(); it.hasNext();) {
			allBooking.add(bookingMap.get(it.next()));
		}
		return allBooking;
	}

	@Override
	public boolean createRecord(Booking b) {
		if(bookingMap.containsKey(b.getID())){
			System.err.println("Error: reservation already exists!");
			return false;	
		} else {
			bookingMap.put(b.getID(), b);
			return true;
		}
	}

	@Override
	public boolean updateRecord(Booking b) {
		if(bookingMap.containsKey(b.getID())) {
			bookingMap.put(b.getID(), b);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteRecord(Booking b) {
		if(bookingMap.containsKey(b.getID())) {
			bookingMap.remove(b.getID());
			return true;
		} else {
			return false;
		}
	}

}
