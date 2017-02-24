package managers;

import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import booking.Booking;
import dao.DAO;
import resources.Resource;
import users.User;

public class BookingManager <T extends Resource<T>> extends Manager<Integer, Booking<T>>{

	public BookingManager(DAO<Integer, Booking<T>> dao) {
		super(dao);
	}

	@Override
	public boolean createRecord(Booking<T> b){

		/*Subroutine to check if reservation with ID is already in map*/
		if(dao.getMap().containsKey(b.getID())){
			System.err.println("Error: ID already exists!");
			return false;
		}

		/*Subroutine to check if record can be stored in map*/
		else {
			ArrayList<Booking<T>> bList = getAllRecords();
			/*Subroutine to get all instances of that resource in booking*/
			for (Booking<T> booking : bList) {
				/*If reservation is open and resource is the same and dates overlap then return false*/
				if(booking.getResource().equals(b.getResource()) && !booking.isClosed() &&  
						dateOverlapping(booking.getDateStart(), b.getDateStart(),
								booking.getDateFinish(), b.getDateFinish())) {
					return false;
				}
			}
			/*If only closed reservations exist on that resource or the required resource is not in the
			 * booking database or dates do not overlap then create record*/
			dao.createRecord(b);
			return true;
		}
	}

	@Override
	public boolean updateRecord(Booking<T> b){
		/*First, check if booking is open and Id is contained in map*/
		if (dao.getMap().containsKey(b.getID()) && !dao.getMap().get(b.getID()).isClosed()) {
			ArrayList<Booking<T>> bList = getAllRecords();
			/*Remove the same reservation from array list*/
			bList.remove(dao.getMap().get(b.getID()));
			/*Subroutine to get all instances of that resource in booking*/
			if (bList.isEmpty()) {
				dao.updateRecord(b);
				return true;
			}
			for (Booking<T> booking : bList) {
				/*If reservation is not the same and resource is the same and dates do not overlap then update*/
				if(booking.getResource().equals(b.getResource()) &&
						!dateOverlapping(booking.getDateStart(), b.getDateStart(),
								booking.getDateFinish(), b.getDateFinish())) {
					dao.updateRecord(b);
					return true;
				}
			}
		}
		return false;
	}

	public boolean updateRecord(Integer ID, User u, Resource<T> t, Date dateStart, Date dateFinish){
		/*First, check if booking is open*/
		if (dao.getMap().containsKey(ID) &&!dao.getMap().get(ID).isClosed()) {		
			ArrayList<Booking<T>> bList = getAllRecords();
			/*Remove the same reservation from array list*/
			bList.remove(dao.getMap().get(ID));
			if (bList.isEmpty()) {
				dao.updateRecord(new Booking<T>(u, t, dateStart, dateFinish, ID));
				return true;
			}
			for (Booking<T> booking : bList) {
				/*If reservation is not the same and resource is the same and dates do not overlap then update*/
				if(t.equals(booking.getResource()) && booking.getID()!=ID &&
						!dateOverlapping(booking.getDateStart(), dateStart,
								booking.getDateFinish(), dateFinish)) {
					dao.updateRecord(new Booking<T>(u, t, dateStart, dateFinish, ID));
					return true;
				}
			}
		}
		return false;
	}
	
	/*Method used when customer picks up a resource*/
	public boolean checkIn(Integer ID){
		if(dao.getMap().containsKey(ID) && !dao.getMap().get(ID).isClosed() &&
				dao.getMap().get(ID).getResource().isAvailable()){
			dao.getMap().get(ID).getResource().setAvailable(false);
			return true;
		} else {
			return false;
		}
	}
	
	/*Method used when a resource is returned*/
	public boolean checkOut(Integer ID){
		if(dao.getMap().containsKey(ID) && !dao.getMap().get(ID).isClosed() &&
				!dao.getMap().get(ID).getResource().isAvailable()){
			dao.getMap().get(ID).setClosed(true);
			dao.getMap().get(ID).getResource().setAvailable(true);
			return true;
		} else {
			return false;
		}
	}
	
	/*Method to assign penalty*/
	public boolean setPenalty(Booking<T> b){
		if (!b.getResource().isAvailable()) {
			DateTime df = new DateTime(dao.getMap().get(b.getID()).getDateFinish());
			DateTime now = new DateTime(new Date());
			b.getUser().setPenalty(now.isAfter(df)? true : false);
			return(now.isAfter(df)? true : false);
		}
		return false;
	}
	
	/*Method to find the first availability (date) of a resource*/
	public Date getFirstAvailability(T res) {
		ArrayList<Booking<T>> bList = getAllRecords();
		DateTime temp1 = new DateTime();
		DateTime temp2 = new DateTime();
		temp1.withDate(2100, 12, 31);
		/*Subroutine to get all instances of res in booking*/
		for (Booking<T> booking : bList) {
			Resource<T> c = booking.getResource();
			if(!booking.isClosed() && c.equals(res)) {
				if(temp1.isAfter(new DateTime(booking.getDateStart()))) {
					temp1 = new DateTime(booking.getDateStart());
					temp2 = new DateTime(booking.getDateFinish());
				}
			}
		}
		return (temp1.isAfterNow()? new DateTime().toDate() : temp2.toDate());
	}

	/*Method to check availability of a resource*/
	public boolean checkResourceAvailability(Resource<T> r) {
		return r.isAvailable();
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