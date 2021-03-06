package resources;

public class Laptop extends Resource {

	private int ram;
	private int nCores;
	private final String BRAND;

	public Laptop(int ID, int ram, int nCores, String BRAND){
		super(ID);
		this.ram = ram;
		this.nCores = nCores;
		this.BRAND = BRAND;
	}

	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}

	public int getnCores() {
		return nCores;
	}
	
	public void setnCores(int nCores) {
		this.nCores = nCores;
	}

	public String getBRAND() {
		return BRAND;
	}
	
	public String toString() {
		String s = ("Laptop ID: " + this.getID() + "\nBrand: " + this.getBRAND() +
				"\nNumber of cores: " + this.getnCores() + "\nRAM: " + this.getRam() +
				"\nAvailable: " + this.isAvailable());
		return s;
	}
	
	public boolean selectByConstraint(int ram) {
		if(this.ram>=ram){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean equals(Resource t) {
		if (this.getID() == t.getID()) {
			return true;
		} else {
			return false;
		}
	}

}