package resources;

public class Car extends Resource {

	private final String NUMBERPLATE;
	private int nSeats;
	private int nDrivers;

	public Car(int ID, String NUMBERPLATE, int nSeats, int nDrivers) {
		super(ID);
		this.NUMBERPLATE = NUMBERPLATE;
		this.nSeats = nSeats;
		this.nDrivers = nDrivers;
	}

	public String getNUMBERPLATE() {
		return NUMBERPLATE;
	}

	public int getnSeats() {
		return nSeats;
	}

	public void setnSeats(int nSeats) {
		this.nSeats = nSeats;
	}

	public int getnDrivers() {
		return nDrivers;
	}

	public void setnDrivers(int nDrivers) {
		this.nDrivers = nDrivers;
	}

	public String toString() {
		String s = ("Car ID: " + this.getID() + "\nNumberplate: " +
				this.getNUMBERPLATE() + "\nNumber of seats: " + this.getnSeats()+
				"\nNumber of drivers: " + this.getnDrivers() +
				"\nAvailable: " + this.isAvailable());
		return s;
	}

	@Override
	public boolean equals(Resource t) {
		if (this.getID() == t.getID()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean selectByConstraint(int nSeat) {
		if (this.nSeats>=nSeat) {
			return true;
		} else {
			return false;
		}
	}

}