package managers;

import java.util.ArrayList;

import dao.DAO;
import users.User;

public class UserManager extends Manager<String, User> {

	public UserManager(DAO<String, User> dao) {
		super(dao);
	}

	public boolean login(User u){
		ArrayList<User> listUser = getAllRecords();
		for (User user : listUser) {
			if (user.getUserName().equals(u.getUserName()) &&
					user.getPwd().equals(u.getPwd()) && !u.isLogged()) {
				u.setLogged(true);
				return true;
			}
		}
		return false;
	}

	public boolean logout(User u){
		if(u.isLogged()) {
			u.setLogged(false);
			return true;
		} else {
			return false;
		}
	}

}
