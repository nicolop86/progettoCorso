package users;

import java.util.Date;
import org.joda.time.DateTime;
import identifier.Identifiable;

public class User implements Identifiable<String> {

	private final String userName;
	private String pwd;
	private String name;
	private String surname;
	private final String identifier;
	private Date birthday;
	private boolean isAdmin;
	
	public User (String name, String surname, String identifier,
			Date birthday, String userName, String pwd) {
		this.name = name;
		this.surname = surname;
		this.identifier = identifier;
		this.birthday = birthday;
		this.userName = userName;
		this.setPwd(pwd);
		this.isAdmin = false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getID() {
		return identifier;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getUserName() {
		return userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String toString() {
		return("Name: " + this.getName() + "\nSurname: " + this.getSurname() + "\nIdentifier" + 
	this.getID() + "\nBirth date: " +
				new DateTime(this.getBirthday()).dayOfMonth().roundFloorCopy().toString());
	}
}
