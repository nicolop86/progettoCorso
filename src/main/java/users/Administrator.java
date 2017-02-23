package users;

import java.util.Date;

public class Administrator extends User {

	public Administrator (String name, String surname, String identifier,
			Date birthday, String userName, String pwd) {
		super(name, surname, identifier, birthday, userName, pwd);
		this.setAdmin(true);
	}
	
}
