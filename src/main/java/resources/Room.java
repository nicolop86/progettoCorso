package resources;

public class Room extends Resource {

	private int capacity;
	private String name;

	public Room(int capacity, String name){
		super();
		this.capacity = capacity;
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		String s = ("Room ID: " + this.getID() + "\nCapacity: " + this.getCapacity() +
				"\nRoom name: " + this.getName());
		return s;
	}
}
