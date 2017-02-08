package testResources;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import resources.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestResource {
	
	ResourceManager<Car> carManager = new ResourceManager<Car>();
	ResourceManager<Laptop> pcManager = new ResourceManager<Laptop>();
	ResourceManager<Projector> projManager = new ResourceManager<Projector>();
	ResourceManager<Room> roomManager = new ResourceManager<Room>();
	Car car1;
	Car car2;
	Car car3;
	Car car4;
	Laptop pc1;
	Laptop pc2;
	Laptop pc3;
	Laptop pc4;
	Projector proj1;
	Projector proj2;
	Projector proj3;
	Projector proj4;
	Room room1;
	Room room2;
	Room room3;
	Room room4;

	@Test
	public void testA() {
		System.out.println("I am in test add element");
		car1 = new Car("AX 896 TR", 4, 1);
		car2 = new Car("FF 874 RR", 5, 2);
		pc1 = new Laptop(8, 4, "Samsung");
		pc2 = new Laptop(4, 2, "Acer");
		proj1 = new Projector(16, "Asus");
		proj2 = new Projector(256, "Panasonic");
		room1 = new Room(40, "Room Blue");
		room2 = new Room(80, "Room Red");
		
		int c1 = carManager.addNewElement(car1);
		int c2 = carManager.addNewElement(car2);
		int p1 = pcManager.addNewElement(pc1);
		int p2 = pcManager.addNewElement(pc2);
		int pr1 = projManager.addNewElement(proj1);
		int pr2 = projManager.addNewElement(proj2);
		int r1 = roomManager.addNewElement(room1);
		int r2 = roomManager.addNewElement(room2);
		
		Assert.assertEquals("Test add new element...", 1, c1);
		Assert.assertEquals("Test add new element...", 0, carManager.getElement(0).getID());
		Assert.assertEquals("Test add new element...", 1, p1);
		Assert.assertEquals("Test add new element...", 2, pcManager.getElement(2).getID());
		Assert.assertEquals("Test add new element...", 1, pr1);
		Assert.assertEquals("Test add new element...", 4, projManager.getElement(4).getID());
		Assert.assertEquals("Test add new element...", 1, r1);
		Assert.assertEquals("Test add new element...", 6, roomManager.getElement(6).getID());
		Assert.assertEquals("Test add new element...", 1, c2);
		Assert.assertEquals("Test add new element...", 1, carManager.getElement(1).getID());
		Assert.assertEquals("Test add new element...", 1, p2);
		Assert.assertEquals("Test add new element...", 3, pcManager.getElement(3).getID());
		Assert.assertEquals("Test add new element...", 1, pr2);
		Assert.assertEquals("Test add new element...", 5, projManager.getElement(5).getID());
		Assert.assertEquals("Test add new element...", 1, r2);
		Assert.assertEquals("Test add new element...", 7, roomManager.getElement(7).getID());
		
		Assert.assertEquals("Test add new element...", true, carManager.getElement(0).isAvailable());
		Assert.assertEquals("Test add new element...", true, carManager.getElement(1).isAvailable());
		Assert.assertEquals("Test add new element...", true, pcManager.getElement(2).isAvailable());
		Assert.assertEquals("Test add new element...", true, pcManager.getElement(3).isAvailable());
		Assert.assertEquals("Test add new element...", true, projManager.getElement(4).isAvailable());
		Assert.assertEquals("Test add new element...", true, projManager.getElement(5).isAvailable());
		Assert.assertEquals("Test add new element...", true, roomManager.getElement(6).isAvailable());
		Assert.assertEquals("Test add new element...", true, roomManager.getElement(7).isAvailable());
	}

	@Test
	public void testB() {
		System.out.println("I am in test to String");
		car3 = new Car("BV 990 TR", 4, 1);
		car4 = new Car("FG 765 RA", 5, 2);
		pc3 = new Laptop(16, 8, "Dell");
		pc4 = new Laptop(6, 2, "Sony");
		proj3 = new Projector(256, "China");
		proj4 = new Projector(1024, "Huawei");
		room3 = new Room(100, "Room Diamond");
		room4 = new Room(60, "Room Crystal");
		
		carManager.addNewElement(car3);
		carManager.addNewElement(car4);
		pcManager.addNewElement(pc3);
		pcManager.addNewElement(pc4);
		projManager.addNewElement(proj3);
		projManager.addNewElement(proj4);
		roomManager.addNewElement(room3);
		roomManager.addNewElement(room4);
		
		Assert.assertEquals("Test to String...", "Car ID: 8\nNumberplate: BV 990 TR\nNumber of seats: 4\nNumber of drivers: 1",
				carManager.getElement(8).toString());
		Assert.assertEquals("Test to String...", "Car ID: 9\nNumberplate: FG 765 RA\nNumber of seats: 5\nNumber of drivers: 2",
				carManager.getElement(9).toString());
		Assert.assertEquals("Test to String...", "Laptop ID: 10\nBrand: Dell\nNumber of cores: 8\nRAM: 16",
				pcManager.getElement(10).toString());
		Assert.assertEquals("Test to String...", "Laptop ID: 11\nBrand: Sony\nNumber of cores: 2\nRAM: 6",
				pcManager.getElement(11).toString());
		Assert.assertEquals("Test to String...", "Projector ID: 12\nColors: 256\nBrand: China",
				projManager.getElement(12).toString());
		Assert.assertEquals("Test to String...", "Projector ID: 13\nColors: 1024\nBrand: Huawei",
				projManager.getElement(13).toString());
		Assert.assertEquals("Test to String...", "Room ID: 14\nCapacity: 100\nRoom name: Room Diamond",
				roomManager.getElement(14).toString());
		Assert.assertEquals("Test to String...", "Room ID: 15\nCapacity: 60\nRoom name: Room Crystal",
				roomManager.getElement(15).toString());
		
		Assert.assertEquals("Test count...", 2, carManager.countElement());
		Assert.assertEquals("Test count...", 2, pcManager.countElement());
		Assert.assertEquals("Test count...", 2, projManager.countElement());
		Assert.assertEquals("Test count...", 2, roomManager.countElement());

	}
	
	@Test
	public void testC() {
	System.out.println("I am in test rent" + Resource.COUNTER);
	
	}
	
}
