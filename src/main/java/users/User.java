package users;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import identifier.Identifiable;

public class User implements Identifiable<String> {

	private String userName;
	private String pwd;
	private String name;
	private String surname;
	private final String identifier;
	private Date birthday;
	private boolean isAdmin;
	private boolean logged;
	private boolean penalty;
	
	public User (String name, String surname, String identifier,
			Date birthday, String userName, String pwd) {
		this.name = name;
		this.surname = surname;
		this.identifier = identifier;
		this.birthday = birthday;
		this.userName = userName;
		this.setPwd(pwd);
		this.isAdmin = false;
		this.logged = false;
		this.penalty = false;
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
	
	public void setUserName(String userName) {
		this.userName=userName;
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
		DateTimeFormatter dtfOut = DateTimeFormat.forPattern("dd-MM-yyyy");
		return("Name: " + this.getName() + "\nSurname: " + this.getSurname() + "\nIdentifier: " + 
	this.getID() + "\nBirth date: " + dtfOut.print(new DateTime(this.getBirthday())));
	}

	public boolean equals(User u){
		if(this.getID().equals(u.getID()))
			return true;
		else {
			return false;
		}
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public boolean isPenalty() {
		return penalty;
	}

	public void setPenalty(boolean penalty) {
		this.penalty = penalty;
	}
	
}
