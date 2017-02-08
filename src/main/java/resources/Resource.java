package resources;

import java.util.Date;

import users.User;

public abstract class Resource {
	
	public static int COUNTER = 0;
	private int ID;
	private boolean isAvailable;
	private Date dateStart;
	private Date dateFinish;
	private User user;
	
	public Resource() {
		this.ID = COUNTER;
		this.isAvailable = true;
		user = null;
		COUNTER++;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
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

	public int getID() {
		return ID;
	}
	
	public abstract String toString();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}