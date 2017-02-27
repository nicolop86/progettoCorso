package booking;

import java.util.Date;

import org.joda.time.DateTime;

import identifier.Identifiable;
import resources.Resource;
import users.User;

public class Booking<T extends Resource> implements Identifiable<Integer> {

	private User user;
	private T resource;
	private Date dateStart;
	private Date dateFinish;
	private int ID;
	private boolean closed;

	public Booking(User user, T t, Date dateStart, Date dateFinish, int ID) {
		this.setUser(user);
		this.setResource(t);
		this.setDateStart(dateStart);
		this.setDateFinish(dateFinish);
		this.setID(ID);
		if(new DateTime().isAfter(new DateTime(dateFinish))) {
			/*This option may never be used when entering a new reservation*/
			this.setClosed(true);
		} else {
			this.setClosed(false);
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public T getResource() {
		return resource;
	}

	public void setResource(T t) {
		this.resource = t;
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
				"\nDate Finish: " + this.dateFinish.toString() + 
				"\nReservation status (closed?): " + this.isClosed());
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

}
