package testDAO;

import java.util.ArrayList;
import java.util.Date;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import booking.Booking;
import dao.DAO;
import resources.Car;
import resources.Laptop;
import users.User;


public class TestBooking {

	Booking b1;
	Booking b2;
	Car c1;
	Laptop pc1;
	User u2;
	User u1;
	ArrayList<Booking> bList = new ArrayList<Booking>();

	@Before
	public void setUp() {
		u1 = new User("Nicolò", "Politi", "PLTNCL86H01M109C",
				new DateTime(1986, 6, 1, 0, 0). toDate(),"nicolo.politi", "pippo");
		c1 = new Car(0, "AZ 007 ZY", 4, 1);
		Booking b1 = new Booking(u1, c1, new Date(), new Date(117, 1, 22), 0);
		bList.add(b1);
		u2 = new User("Luca", "Rossi", "LCARSS88H01B549V",
				new DateTime(1988, 6, 1, 0, 0).toDate(),"luca.rossi", "pippa");
		pc1 = new Laptop(1, 8, 4, "Toshiba");
		b2 = new Booking(u2, pc1, new Date(), new Date(117, 1, 22), 1);
	}

	@Test
	public void test() {
		DAO<Integer, Booking> daoBooking = new DAO<Integer, Booking>(bList);
		Assert.assertEquals(false, daoBooking.updateRecord(b2));
		Assert.assertEquals(true, daoBooking.createRecord(b2));
		b2.setUser(u1);
		Assert.assertEquals(true, daoBooking.updateRecord(b2));
		ArrayList<Booking> bListNew = daoBooking.getAll();
		System.out.println("===========");
		for (Booking booking : bListNew) {
			System.out.println(booking.toString());
		}
		Assert.assertEquals(true, daoBooking.deleteRecord(b2));
		Assert.assertEquals(false, daoBooking.deleteRecord(b2));		
		bListNew = daoBooking.getAll();
		System.out.println("===========");
		for (Booking booking : bListNew) {
			System.out.println(booking.toString());
		}
	}

}
