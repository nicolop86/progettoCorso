package booking;

import java.util.Date;
import identifier.Identifiable;
import resources.Resource;
import users.User;

public class Booking implements Identifiable<Integer> {
	
	private User user;
	private Resource resource;
	private Date dateStart;
	private Date dateFinish;
	private int ID;
	
	public Booking(User user, Resource resource, Date dateStart, Date dateFinish, int ID) {
		this.setUser(user);
		this.setResource(resource);
		this.setDateStart(dateStart);
		this.setDateFinish(dateFinish);
		this.setID(ID);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
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

	public void setID(int ID) {
		this.ID = ID;
	}
	
	public String toString() {
		return("Reservation: " + this.getID() + "\n" + resource.toString() +
				"\n" + user.toString() + "\nDate Start: " + this.dateStart.toString() + 
				"\nDate Finish: " + this.dateFinish.toString());
	}

}
