package managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import booking.Booking;
import dao.DAO;
import resources.Resource;

public class BookingManager extends Manager<Integer, Booking>{

	public BookingManager(DAO<Integer, Booking> dao) {
		super(dao);
	}

	public boolean createRecord(Booking b){
		
		Resource<?> r = b.getResource();
		TreeMap<Integer, Booking> tMap = dao.getMap();
		
		/*Subroutine to check if reservation with ID is already in map*/
		if(tMap.containsKey(b.getID())){
			System.err.println("Error: ID already exists!");
			return false;
		}
		
		/*Subroutine to check if record can be stored in map*/
		else {
			ArrayList<Booking> bList = getAllRecords();
			ArrayList<Integer> temp = new ArrayList<Integer>();
			/*Subroutine to get all instances of that resource in booking*/
			for (Booking booking : bList) {
				 Resource<?> c = booking.getResource();
				if(c.equals(r)) {
					temp.add(booking.getID());
				}
			}
			if (temp.size() == 0){
				/*If it does not exist in booking, then create*/
				dao.createRecord(b);
				return true;
			} else {
			for (Integer integer : temp) {
				/*If it exists in booking and dates overlap, then return with false*/
				Booking tempBook = tMap.get(integer);
				if(dateOverlapping(tempBook.getDateStart(),	b.getDateStart(),
						tempBook.getDateFinish(), b.getDateFinish())) {
					return false;
				}
			}
			}
			/*If it exists but dates do not overlap, check if it is in house and then add reservation*/
			if (check(b)) {
			dao.createRecord(b);
			return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean updateRecord(Booking b){
		TreeMap<Integer, Booking> tMap = dao.getMap();
		if(tMap.containsKey(b.getID()) && 
				dateOverlapping(tMap.get(b.getID()).getDateStart(), b.getDateStart(),
						tMap.get(b.getID()).getDateFinish(), b.getDateFinish())){
			dao.updateRecord(b);
			return true;
		} else {
			return false;
		}
	}
	
	/*Method to check availability of a resource*/
	public boolean check(Booking b) {
		return b.getResource().isAvailable();
	}
	
	private boolean dateOverlapping(Date ds1, Date ds2, Date df1, Date df2) {
		DateTime dts1 = new DateTime(ds1);
		DateTime dts2 = new DateTime(ds2);
		DateTime dtf1 = new DateTime(df1);
		DateTime dtf2 = new DateTime(df2);
		Interval int1 = new Interval(dts1, dtf1);
		Interval int2 = new Interval(dts2, dtf2);
		return(int1.overlaps(int2));
	}
}