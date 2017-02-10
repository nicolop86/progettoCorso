package testDAO;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DAO;
import managers.Manager;
import resources.*;

public class TestResources {

	ArrayList<Resource> resList = new ArrayList<Resource>();
	Car c1;
	Car c2;
	Room room1;
	Room room2;
	Room room3;
	Laptop pc1;
	Laptop pc2;
	Projector proj1;
	
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

		resList.add(c2);
		resList.add(room1);
		resList.add(room3);
		resList.add(pc1);
	}
	
	@Test
	public void test() {
		DAO <Integer, Resource> daoRes = new DAO<Integer, Resource>(resList);
		Manager<Integer, Resource> resourceManager = new Manager<Integer, Resource>(daoRes);
		Assert.assertEquals(true, resourceManager.createRecord(c1));
		Assert.assertEquals(false, resourceManager.createRecord(c2));
		Assert.assertEquals(true, resourceManager.createRecord(room2));
		Assert.assertEquals(true, resourceManager.createRecord(proj1));
		Assert.assertEquals(true, resourceManager.createRecord(pc2));
		pc2.setRam(8);
		c2.setnDrivers(2);
		room1.setName("Orange Room");
		Laptop pc3 = new Laptop(8, 12, 4, "Samsung");
		Assert.assertEquals(true, resourceManager.updateRecord(pc2));
		Assert.assertEquals(true, resourceManager.updateRecord(c2));
		Assert.assertEquals(false, resourceManager.updateRecord(pc3));
		Assert.assertEquals(true, resourceManager.updateRecord(room1));
		Assert.assertEquals(false, resourceManager.deleteRecord(pc3));
		Assert.assertEquals(true, resourceManager.deleteRecord(pc2));
		
		ArrayList<Resource> tempResList = resourceManager.getAllRecords();
		System.out.println("=============");
		for (Resource resource : tempResList) {
			System.out.println(resource.toString());
		}
	}

}
