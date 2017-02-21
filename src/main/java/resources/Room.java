package resources;

public class Room extends Resource<Room> {

	private int capacity;
	private String name;

	public Room(int ID, int capacity, String name){
		super(ID);
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
	
	public boolean equals(Room room){
		if (this.getID() == room.getID()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean constraint(int capacity) {
		if(this.capacity>=capacity){
			return true;
		} else {
			return false;
		}
	}
	
}
