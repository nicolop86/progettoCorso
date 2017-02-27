package testSystem;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import booking.Booking;
import dao.DAO;
import managers.BookingManager;
import managers.ResourceManager;
import resources.Car;
import resources.Laptop;
import resources.Projector;
import resources.Room;
import users.User;


public class TestBooking {

	User u2;
	User u1;
	Car c1;
	Car c2;
	Laptop pc1;
	Laptop pc2;
	Projector proj1;
	Projector proj2;
	Room room1;
	Room room2;
	Room room3;

	Booking<Car> b1;
	Booking<Laptop> b2;
	Booking<Laptop> b3;
	Booking<Projector> b4;
	Booking<Room> b5;

	DAO<Integer, Car> daoCar;
	DAO<Integer, Laptop> daoPc;
	DAO<Integer, Projector> daoProj;
	DAO<Integer, Room> daoRoom;
	ResourceManager<Car> carManager;
	ResourceManager<Laptop> pcManager;
	ResourceManager<Projector> projManager;
	ResourceManager<Room> roomManager;

	DAO<Integer, Booking<Car>> daoCarBooking = new DAO<Integer, Booking<Car>>();
	DAO<Integer, Booking<Laptop>> daoPcBooking = new DAO<Integer, Booking<Laptop>>();
	DAO<Integer, Booking<Projector>> daoProjBooking = new DAO<Integer, Booking<Projector>>();
	DAO<Integer, Booking<Room>> daoRoomBooking = new DAO<Integer, Booking<Room>>();

	BookingManager<Car> bCarManager = new BookingManager<Car>(daoCarBooking);
	BookingManager<Laptop> bPcManager = new BookingManager<Laptop>(daoPcBooking);
	BookingManager<Projector> bProjManager = new BookingManager<Projector>(daoProjBooking);
	BookingManager<Room> bRoomManager = new BookingManager<Room>(daoRoomBooking);

	@Before
	public void setup() {

		/*Creating users*/
		u1 = new User("Nicolò", "Politi", "PLTNCL86H01M109C",
				new DateTime(1986, 6, 1, 0, 0).toDate(),"nicolo.politi", "pippo");
		u2 = new User("Luca", "Rossi", "LCARSS88H01B549V",
				new DateTime(1988, 7, 19, 0, 0).toDate(),"luca.rossi", "pippa");

		/*Creating cars*/
		c1 = new Car(0, "AZ 007 ZY", 4, 1);
		c2 = new Car(1, "BZ 453 VB", 7, 1);

		/*Creating laptops*/
		pc1 = new Laptop(2, 8, 4, "Toshiba");
		pc2 = new Laptop(3, 16, 8, "Dell");

		/*Creating projectors*/
		proj1 = new Projector(6, 256, "Sony");
		proj2 = new Projector(8, 1024, "Samsung");

		/*Creating rooms*/
		room1 = new Room(4, 200, "Blue room");
		room2 = new Room(5, 20, "Red room");
		room3 = new Room(7, 10, "Green room");

		/*Creating resources managers*/
		daoCar = new DAO<Integer, Car>();
		daoCar.createRecord(c1);
		daoCar.createRecord(c2);
		daoPc = new DAO<Integer, Laptop>();
		daoPc.createRecord(pc1);
		daoPc.createRecord(pc2);
		daoProj = new DAO<Integer, Projector>();
		daoProj.createRecord(proj1);
		daoProj.createRecord(proj2);
		daoRoom = new DAO<Integer, Room>();
		daoRoom.createRecord(room1);
		daoRoom.createRecord(room2);
		daoRoom.createRecord(room3);
		/*===============================*/
		carManager = new ResourceManager<>(daoCar);
		pcManager = new ResourceManager<>(daoPc);
		projManager = new ResourceManager<>(daoProj);
		roomManager = new ResourceManager<>(daoRoom);

		/*Creating reservations*/
		b1 = new Booking<Car>(u1, c1, new DateTime(2017, 2, 14, 8, 0).toDate(), new DateTime(2017, 3, 2, 10, 0).toDate(), 0);
		b2 = new Booking<Laptop>(u2, pc1, new DateTime(2017, 2, 28, 14, 0).toDate(), new DateTime(2017, 3, 1, 18, 0).toDate(), 1);
		b3 = new Booking<Laptop>(u1, pc1, new DateTime(2017, 2, 10, 10, 0).toDate(), new DateTime(2017, 2, 15, 14, 0).toDate(), 2);
		b4 = new Booking<Projector>(u2, proj1, new DateTime(2017, 3, 20, 10, 0).toDate(), new DateTime(2017, 3, 21, 14, 0).toDate(), 3);
		b5 = new Booking<Room>(u1, room1, new DateTime(2017, 3, 20, 10, 0).toDate(), new DateTime(2017, 3, 21, 14, 0).toDate(), 4);

		/*Creating booking managers*/
		daoCarBooking.createRecord(b1);
		daoPcBooking.createRecord(b2);
		daoPcBooking.createRecord(b3);
		daoProjBooking.createRecord(b4);
		daoRoomBooking.createRecord(b5);

	}

	@Test
	public void test(){
		/*Test on adding new records to booking*/
		/*Checking that free resources can be reserved*/
		Assert.assertEquals(false, b1.isClosed());
		Assert.assertEquals(true, bCarManager.createRecord
				(new Booking<Car>(u2, c2, new DateTime(2017, 3, 1, 8, 0).toDate(), new DateTime(2017, 3, 2, 10, 0).toDate(), 5)));
		/*Checking that same ID gives false*/
		Assert.assertEquals(false, bCarManager.createRecord
				(new Booking<Car>(u2, c2, new DateTime(2017, 4 , 1, 8, 0).toDate(), new DateTime(2017, 4, 2, 10, 0).toDate(), 5)));
		/*Checking that overlapping dates are not possible*/
		Assert.assertEquals(false, bCarManager.createRecord
				(new Booking<Car>(u1, c2, new DateTime(2017, 3, 1, 8, 0).toDate(), new DateTime(2017, 3, 4, 8, 0).toDate(), 6)));
		/*Checking that a new resource can be used for a new booking*/
		Car c3 = new Car(9, "CC 674 RE", 2, 1);
		Assert.assertEquals(true, bCarManager.createRecord
				(new Booking<Car>(u2, c3, new DateTime(2017, 3, 1, 8, 0).toDate(), new DateTime(2017, 3, 4, 8, 0).toDate(), 6)));
		
		
		/*Test on updating records*/
		/*Checking that a booking with no ID in booking table cannot be updated*/
		Assert.assertEquals(false, bPcManager.updateRecord
				(new Booking<Laptop>(u2, pc1, new DateTime(2017, 3, 1, 10, 0).toDate(), new DateTime(2017, 3, 2, 14, 0).toDate(), 100)));
		/*Checking that update with overlapping dates with another reservation is not possible*/
		Booking<Car> b6 = new Booking<Car>(u2, c1, new DateTime(2017, 3, 3, 8, 0).toDate(), new DateTime(2017, 3, 3, 18, 0).toDate(), 5);
		Assert.assertEquals(false, bCarManager.updateRecord(new Booking<Car>(b6.getUser(), b6.getResource(), new DateTime(2017, 3, 1, 8, 0).toDate(),
				b6.getDateFinish(), b6.getID())));
		/*Checking that update of a closed reservation cannot be done*/
		Assert.assertEquals(false, bPcManager.updateRecord
				(new Booking<Laptop>(u1, pc1, new DateTime(2017, 3, 1, 8, 0).toDate(), new DateTime(2017, 3, 1, 18, 0).toDate(), 2)));
		/*Checking that a booking with ID in booking table can be updated*/
		Assert.assertEquals(true, bProjManager.updateRecord
				(new Booking<Projector>(u2, proj1, new DateTime(2017, 3, 1, 8, 0).toDate(), new DateTime(2017, 3, 1, 18, 0).toDate(), b4.getID())));
		Assert.assertEquals(true, bProjManager.updateRecord(3, u1, proj1,
				new DateTime(2017, 3, 1, 8, 0).toDate(), new DateTime(2017, 3, 1, 18, 0).toDate()));
		Assert.assertEquals(true, bCarManager.updateRecord(new Booking<Car>(b6.getUser(), b6.getResource(), new DateTime(2017, 5, 1, 8, 0).toDate(),
				new DateTime(2017, 5, 2, 8, 0).toDate(), b6.getID())));
		Assert.assertEquals(true, bCarManager.updateRecord(b6.getID(), b6.getUser(), b6.getResource(),
				new DateTime(2017, 5, 3, 8, 0).toDate(), new DateTime(2017, 5, 5, 18, 0).toDate()));
	
		/*Checking first availability method*/
		Assert.assertEquals(new DateTime(2017, 3, 2, 10, 0).toDate(), bCarManager.getFirstAvailability(c1));
		Assert.assertEquals(new DateTime().toDate(), bProjManager.getFirstAvailability(proj1));

		/*Checking if a resource is available*/
		Assert.assertEquals(true, bRoomManager.checkResourceAvailability(room1));

		/*Check in method*/
		Assert.assertEquals(true, bCarManager.checkIn(0));

		/*Checking toString method of booking*/
		Assert.assertEquals("Reservation: 0\nCar ID: 0\nNumberplate: AZ 007 ZY\nNumber of seats: 4\nNumber of drivers: 1"
				+ "\nAvailable: false\nName: Nicolò\nSurname: Politi\n"
				+ "Identifier: PLTNCL86H01M109C\nBirth date: 01-06-1986\nDate Start: Tue Feb 14 08:00:00 CET 2017"
				+ "\nDate Finish: Thu Mar 02 10:00:00 CET 2017\nReservation status (closed?): false", b1.toString());

		/*Checkout method on an almost expired booking*/
		Booking<Projector> b7 = new Booking<Projector>(u1, proj1,
				new DateTime(2017, 2, 27, 8, 0).toDate(), new DateTime(2017, 2, 27, 12, 0).toDate(), 8);
		bProjManager.createRecord(b7);
		bProjManager.checkIn(8);
		Assert.assertEquals(true, bProjManager.checkOut(8));
		Assert.assertEquals(false, bProjManager.checkOut(8));

		/*Checking method to set penalty*/
		Booking<Room> b8 = new Booking<Room>(u2, room3, new DateTime(2017, 2, 10, 10, 0).toDate(),
				new DateTime(2017, 2, 12, 8, 0).toDate(), 9);
		b8.getResource().setAvailable(false);
		bRoomManager.createRecord(b8);
		Assert.assertEquals(true, bRoomManager.setPenalty(b8));
		Assert.assertEquals(false, bCarManager.setPenalty(b1));
		Booking<Room> b9 = new Booking<Room>(u2, room1, new DateTime(2017, 4, 10, 10, 0).toDate(),
				new DateTime(2017, 4, 12, 8, 0).toDate(), 10);
		Assert.assertEquals(false, bRoomManager.setPenalty(b9));
	}

}
