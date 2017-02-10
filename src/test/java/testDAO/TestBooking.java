package testDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import booking.Booking;
import dao.DAO;
import managers.BookingManager;
import managers.Manager;
import resources.Car;
import resources.Laptop;
import users.User;


public class TestBooking {

	Booking b1;
	Booking b2;
	Booking b3;
	Car c1;
	Laptop pc1;
	User u2;
	User u1;
	ArrayList<Booking> bList = new ArrayList<Booking>();

	@Test
	public void test() {
		u1 = new User("Nicolò", "Politi", "PLTNCL86H01M109C",
				new DateTime(1986, 6, 1, 0, 0). toDate(),"nicolo.politi", "pippo");
		c1 = new Car(0, "AZ 007 ZY", 4, 1);
		Booking b1 = new Booking(u1, c1, new Date(), new Date(117, 1, 22), 0);
		bList.add(b1);
		u2 = new User("Luca", "Rossi", "LCARSS88H01B549V",
				new DateTime(1988, 6, 1, 0, 0).toDate(),"luca.rossi", "pippa");
		pc1 = new Laptop(1, 8, 4, "Toshiba");
		b2 = new Booking(u2, pc1, new Date(), new Date(117, 1, 22), 1);
		b3 = new Booking(u1, pc1, new Date(117, 1, 15), new Date(117, 1, 22), 2);
		
		DAO<Integer, Booking> daoBooking = new DAO<Integer, Booking>(bList);
		BookingManager bManager = new BookingManager(daoBooking);
		Assert.assertEquals(true, bManager.createRecord(b2));
		ArrayList<Booking> bList = bManager.getAllRecords();
		Assert.assertEquals(false, bManager.createRecord(b3));
		b3.setDateStart(new Date(117, 1, 23));
		b3.setDateFinish(new Date(117, 1, 24));
		Assert.assertEquals(true, bManager.createRecord(b3));		
/*		Assert.assertEquals(false, bManager.updateRecord(b2));
		b2.setUser(u1);
		Assert.assertEquals(true, bManager.updateRecord(b2));
		ArrayList<Booking> bListNew = bManager.getAllRecords();
		System.out.println("===========");
		for (Booking booking : bListNew) {
			System.out.println(booking.toString());
		}
		Assert.assertEquals(true, bManager.deleteRecord(b2));
		Assert.assertEquals(false, bManager.deleteRecord(b2));		
		bListNew = bManager.getAllRecords();
		System.out.println("===========");
		for (Booking booking : bListNew) {
			System.out.println(booking.toString());
		}*/
	}

}
