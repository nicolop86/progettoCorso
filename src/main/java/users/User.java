package users;

import java.util.Date;

public class User {
	
	private String name;
	private String surname;
	private final String identifier;
	private Date birthday;
	
	public User(String name, String surname, String identifier, Date birthday) {
		this.name = name;
		this.surname = surname;
		this.identifier = identifier;
		this.birthday = birthday;
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
	
	public String getIdentifier() {
		return identifier;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
