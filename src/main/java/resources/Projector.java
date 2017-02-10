package resources;

public class Projector extends Resource<Projector> {
	
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
		String s = ("Projector ID: " + this.getID() + "\nColors: " + this.getnColors() +
				"\nBrand: " + this.getBRAND());
		return s;
	}
	
	public boolean equals(Projector proj){
		if (this.getID() == proj.getID()) {
			return true;
		} else {
			return false;
		}
	}
}