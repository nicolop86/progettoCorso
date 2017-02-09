package managers;

import java.util.ArrayList;
import java.util.Date;
import dao.DAO;
import identifier.Identifiable;
import resources.Resource;
import users.User;
import org.joda.time.*;

import booking.Booking;

public class ResourceManager {
	
	private DAO<Integer, Resource> dao;

	public ResourceManager(DAO<Integer, Resource> dao) {
		this.dao = dao;
	}
	
	public boolean book(User u, Resource r, Date dateStart, Date dateFinish) {
		DateTime ds = new DateTime(dateStart);
		DateTime df = new DateTime(dateFinish);
		ArrayList<Resource> aList = dao.getAll();
		if (aList.contains(r)) {
		} else {
			Booking b = new Booking(u, r, dateStart, dateFinish, 4);
			dao.createRecord();
		}
		return false;
	}

}