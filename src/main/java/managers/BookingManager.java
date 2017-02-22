package managers;

import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import booking.Booking;
import dao.DAO;
import resources.Resource;

public class BookingManager extends Manager<Integer, Booking>{

	public BookingManager(DAO<Integer, Booking> dao) {
		super(dao);
	}

	@Override
	public boolean createRecord(Booking b){

		/*Subroutine to check if reservation with ID is already in map*/
		if(dao.getMap().containsKey(b.getID())){
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
				if(!booking.isClosed() && c.equals(b.getResource())) {
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
					Booking tempBook = dao.getMap().get(integer);
					if(dateOverlapping(tempBook.getDateStart(),	b.getDateStart(),
							tempBook.getDateFinish(), b.getDateFinish())) {
						return false;
					}
				}
			}
			/*If it exists but dates do not overlap, then add reservation*/
			dao.createRecord(b);
			return true;
		}
	}

	@Override
	public boolean updateRecord(Booking b){
		if(dao.getMap().containsKey(b.getID()) && 
				!dateOverlapping(dao.getMap().get(b.getID()).getDateStart(), b.getDateStart(),
						dao.getMap().get(b.getID()).getDateFinish(), b.getDateFinish())){
			dao.updateRecord(b);
			return true;
		} else {
			return false;
		}
	}

	/*Method used when a resource is returned*/
	public boolean checkOut(Booking b){
		if(dao.getMap().containsKey(b.getID())){
			b.setClosed(true);
			b.getResource().setAvailable(true);
			return true;
		}
		else {
			return false;
		}
	}

	/*Method to assign penalty*/
	public boolean setPenalty(Booking b){
		DateTime df = new DateTime(dao.getMap().get(b.getID()).getDateFinish());
		DateTime now = new DateTime(new Date());
		b.getUser().setPenalty(now.isAfter(df)? true : false);
		return(now.isAfter(df)? true : false);
	}
	
	/*Method to find the first availability (date) of a resource*/
	public Date getFirstAvailability(Resource<?> res) {
		ArrayList<Booking> bList = getAllRecords();
		DateTime temp1 = new DateTime();
		DateTime temp2 = new DateTime();
		temp1.withDate(2100, 12, 31);
		/*Subroutine to get all instances of res in booking*/
		for (Booking booking : bList) {
			Resource<?> c = booking.getResource();
			if(!booking.isClosed() && c.equals(res)) {
				if(temp1.isAfter(new DateTime(booking.getDateStart()))) {
					temp1 = new DateTime(booking.getDateStart());
					temp2 = new DateTime(booking.getDateFinish());
				}
			}
		}
		return (temp1.isAfterNow()? new Date() : temp2.toDate());
	}

	/*Method to check availability of a resource*/
	public boolean check(Booking b) {
		return b.getResource().isAvailable();
	}

	/*Method to check if 2 dates of booking objects are overlapping*/
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