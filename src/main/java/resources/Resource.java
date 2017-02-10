package resources;

import identifier.Identifiable;

public abstract class Resource<T extends Resource<?>> implements Identifiable<Integer> {
	
	private int ID;
	
	public Resource(int ID) {
		this.ID = ID;
	}

	public Integer getID() {
		return Integer.valueOf(ID);
	}

	public abstract String toString();
	public abstract boolean equals(T t);

}