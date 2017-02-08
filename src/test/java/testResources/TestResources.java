package testResources;

import org.junit.Assert;
//import org.junit.Before;
import org.junit.Test;

import resources.*;

public class TestResources {

	Car car1 = new Car("AX 896 TR", 4, 1);
	Car car2 = new Car("FF 874 RR", 5, 2);
	
	Laptop pc1 = new Laptop(8, 4, "Samsung");
	Laptop pc2 = new Laptop(4, 2, "Acer");
	
	Projector proj1 = new Projector(16, "Asus");
	Projector proj2 = new Projector(256, "Panasonic");
	
	Room room1 = new Room(40, "Room Blue");
	Room room2 = new Room(80, "Room Red");
	
	ResourceManager<Car> carManager = new ResourceManager<Car>();
	ResourceManager<Laptop> pcManager = new ResourceManager<Laptop>();
	ResourceManager<Projector> projManager = new ResourceManager<Projector>();
	ResourceManager<Room> roomManager = new ResourceManager<Room>();
			
	@Test
	public void test() {
		Assert.assertEquals("Test add new element...", 1, carManager.addNewElement(car1));
		Assert.assertEquals("Test add new element...", 0, carManager.getElement(0).getID());
		Assert.assertEquals("Test add new element...", 1, pcManager.addNewElement(pc1));
		Assert.assertEquals("Test add new element...", 2, pcManager.getElement(2).getID());
		Assert.assertEquals("Test add new element...", 1, projManager.addNewElement(proj1));
		Assert.assertEquals("Test add new element...", 4, projManager.getElement(4).getID());
		Assert.assertEquals("Test add new element...", 1, roomManager.addNewElement(room1));
		Assert.assertEquals("Test add new element...", 6, roomManager.getElement(6).getID());
		
	}

}
