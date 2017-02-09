package resources;

import java.util.Date;
import identifier.Identifiable;

public abstract class Resource implements Identifiable<Integer> {
	
	private int ID;
	private Date dateStart;
	private Date dateFinish;
	
	public Resource(int ID) {
		this.ID = ID;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}

	public Integer getID() {
		return Integer.valueOf(ID);
	}
	
	public abstract String toString();

}