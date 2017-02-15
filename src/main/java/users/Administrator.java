package users;

import java.util.Date;
import managers.UserManager;

public class Administrator extends User {

	public Administrator (String name, String surname, String identifier,
			Date birthday, String userName, String pwd, UserManager uManager) {
		super(name, surname, identifier, birthday, userName, pwd, uManager);
		this.setAdmin(true);
	}

	public boolean updateUser(User u) {
		if (this.isLogged()) {
		return uManager.updateRecord(u);
		}
		else {
			return false;
		}
	}

	public boolean deleteUser(User u){
		if (this.isLogged()) {
		return uManager.deleteRecord(u);
		}
		else {
			return false;
		}
	}
	
}
