package resources;

public class Projector extends Resource {
	
	private int nColors;
	private final String BRAND;
	
	public Projector(int nColors, String BRAND) {
		super();
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
	
	public int rentMe() {
		int n=0;
		
		return n;
	}
	
	public String toString() {
		String s = ("Projector ID: " + this.getID() + "\nColors: " + this.getnColors() +
				"\nBrand: " + this.getBRAND());
		return s;
	}
}