package resources;

public class Projector extends Resource {

	private int nColors;
	private final String BRAND;

	public Projector(int ID, int nColors, String BRAND) {
		super(ID);
		this.nColors = nColors;
		this.BRAND = BRAND;
	}	

	public int getnColors() {
		return nColors;
	}

	public void setnColors(int nColors) {
		this.nColors = nColors;
	}

	public String getBRAND() {
		return BRAND;
	}

	public String toString() {
		String s = ("Projector ID: " + this.getID() + "\nColors: " +
				this.getnColors() + "\nBrand: " + this.getBRAND() +
				"\nAvailable: " + this.isAvailable());
		return s;
	}

	public boolean selectByConstratin(int nColors) {
		if(this.nColors>=nColors){
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