package testSystem;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DAO;
import managers.ResourceManager;
import resources.*;

public class TestResources {

	ArrayList<Car> carList = new ArrayList<>();
	ArrayList<Projector> projList = new ArrayList<>();
	ArrayList<Laptop> pcList = new ArrayList<>();
	ArrayList<Room> roomList = new ArrayList<>();
	Car c1;
	Car c2;
	Car c3;
	Room room1;
	Room room2;
	Room room3;
	Laptop pc1;
	Laptop pc2;
	Projector proj1;
	Projector proj2;

	@Before
	public void setup() {
		c1 = new Car(0, "AZ 007 ZY", 4, 1);
		c2 = new Car(1, "BZ 453 VB", 4, 1);
		room1 = new Room(2, 200, "Blue room");
		room2 = new Room(3, 20, "Red room");
		room3 = new Room(4, 10, "Green room");
		pc1 = new Laptop(5, 8, 4, "Toshiba");
		pc2 = new Laptop(7, 16, 8, "Dell");
		proj1 = new Projector(6, 256, "Sony");
		proj2 = new Projector(8, 1024, "Samsung");

		carList.add(c2);
		roomList.add(room1);
		roomList.add(room3);
		pcList.add(pc1);
		projList.add(proj1);
	}

	@Test
	public void test() {
		DAO<Integer, Car> daoCar = new DAO<Integer, Car>(carList);
		ResourceManager<Car> carManager = new ResourceManager<Car>(daoCar);
		DAO <Integer, Room> daoRoom = new DAO<Integer, Room>(roomList);
		ResourceManager<Room> roomManager = new ResourceManager<Room>(daoRoom);
		DAO <Integer, Laptop> daoPc = new DAO<Integer, Laptop>(pcList);
		ResourceManager<Laptop> pcManager = new ResourceManager<Laptop>(daoPc);
		DAO <Integer, Projector> daoProj = new DAO<Integer, Projector>(projList);
		ResourceManager<Projector> projManager = new ResourceManager<Projector>(daoProj);

		/*Tests on create records*/
		Assert.assertEquals(true, carManager.createRecord(c1));
		Assert.assertEquals(true, roomManager.createRecord(room2));
		Assert.assertEquals(true, pcManager.createRecord(pc2));
		Assert.assertEquals(true, projManager.createRecord(proj2));
		Assert.assertEquals(false, carManager.createRecord(c1));
		Assert.assertEquals(false, roomManager.createRecord(room2));
		Assert.assertEquals(false, pcManager.createRecord(pc2));
		Assert.assertEquals(false, projManager.createRecord(proj2));

		/*Tests on get element by ID*/
		Assert.assertEquals(c1, carManager.getElement(0));
		Assert.assertEquals(pc1, pcManager.getElement(5));
		Assert.assertEquals(room3, roomManager.getElement(4));
		Assert.assertEquals(proj2, projManager.getElement(8));

		/*Tests on update records*/
		Assert.assertEquals(true, carManager.updateRecord(new Car(1, "YY 938 NN", 5, 1)));
		Assert.assertEquals(false, carManager.updateRecord(new Car(10, "YY 938 NN", 4, 1)));
		Assert.assertEquals(false, pcManager.updateRecord(new Laptop(14, 8, 8, "Toshiba")));
		Assert.assertEquals(true, pcManager.updateRecord(new Laptop(5, 8, 8, "Toshiba")));
		Assert.assertEquals(false, roomManager.updateRecord(new Room(15, 25, "Ruby Room")));
		Assert.assertEquals(true, roomManager.updateRecord(new Room(4, 10, "Ruby Room")));
		Assert.assertEquals(false, projManager.updateRecord(new Projector(16, 1024, "HP")));
		Assert.assertEquals(true, projManager.updateRecord(new Projector(8, 256, "Samsung")));

		/*Tests on select by constraint*/

		ArrayList<Car> restrictedCarList = carManager.returnByConstrain(5);
		ArrayList<Laptop> restrictedPcList = pcManager.returnByConstrain(4);
		ArrayList<Projector> restrictedProjList = projManager.returnByConstrain(256);
		ArrayList<Room> restrictedRoomList = roomManager.returnByConstrain(20);

		ArrayList<Car> aCarArray = new ArrayList<>();
		aCarArray.add(new Car(1, "YY 938 NN", 5, 1));

		ArrayList<Laptop> aLaptopArray = new ArrayList<>();
		aLaptopArray.add(new Laptop(5, 8, 8, "Toshiba"));
		aLaptopArray.add(pc2);

		ArrayList<Projector> aProjArray = new ArrayList<>();
		aProjArray.add(proj1);
		aProjArray.add(new Projector(8, 256, "Samsung"));

		ArrayList<Room> aRoomArray = new ArrayList<>();
		aRoomArray.add(room1);
		aRoomArray.add(room2);

		String expected = null;		
		String actual = null;

		/*Comparing restricted array of cars*/
		if(!restrictedCarList.isEmpty())
			for (Car car : restrictedCarList) {
				expected+=(car.toString());
			}

		if(!aCarArray.isEmpty())
			for (Car car : aCarArray) {
				actual+=(car.toString());
			}

		Assert.assertEquals(expected, actual);

		/*Comparing restricted array of laptops*/
		expected = null;

		if(!restrictedPcList.isEmpty())
			for (Laptop pc : restrictedPcList) {
				expected+=(pc.toString());
			}

		actual = null;

		if(!aLaptopArray.isEmpty())
			for (Laptop pc : aLaptopArray) {
				actual+=(pc.toString());
			}

		Assert.assertEquals(expected, actual);

		/*Comparing restricted array of projectors*/
		expected = null;

		if(!restrictedProjList.isEmpty())
			for (Projector proj : restrictedProjList) {
				expected+=(proj.toString());
			}

		actual = null;

		if(!aProjArray.isEmpty())
			for (Projector proj : aProjArray) {
				actual+=(proj.toString());
			}

		Assert.assertEquals(expected, actual);

		/*Comparing restricted array of rooms*/
		expected = null;

		if(!restrictedRoomList.isEmpty())
			for (Room room : restrictedRoomList) {
				expected+=(room.toString());
			}

		actual = null;

		if(!aRoomArray.isEmpty())
			for (Room room : aRoomArray) {
				actual+=(room.toString());
			}

		Assert.assertEquals(expected, actual);

		/*Tests on delete records*/
		Assert.assertEquals(true, carManager.deleteRecord(1));
		Assert.assertEquals(false, carManager.deleteRecord(10));
		Assert.assertEquals(false, pcManager.deleteRecord(14));
		Assert.assertEquals(true, pcManager.deleteRecord(5));
		Assert.assertEquals(false, roomManager.deleteRecord(15));
		Assert.assertEquals(true, roomManager.deleteRecord(4));
		Assert.assertEquals(false, projManager.deleteRecord(16));
		Assert.assertEquals(true, projManager.deleteRecord(8));

	}

}
