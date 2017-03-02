package resources;

import identifier.Identifiable;

public abstract class Resource implements Identifiable<Integer> {
	
	private int ID;
	private boolean isAvailable;
	
	public Resource(int ID) {
		this.ID = ID;
		this.isAvailable = true;
	}

	public Integer getID() {
		return Integer.valueOf(ID);
	}

	public abstract String toString();
	public abstract boolean equals(Resource t);
	public abstract boolean selectByConstraint(int param);

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isInHouse) {
		this.isAvailable = isInHouse;
	}

}