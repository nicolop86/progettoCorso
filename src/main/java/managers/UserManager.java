package managers;

import java.util.ArrayList;
import java.util.Date;

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
	
	public boolean updateRecord(String ID, String name, String surname,
			Date birthday, String userName, String pwd){
		if(dao.getMap().containsKey(ID)){
			dao.updateRecord(new User(name, surname, ID, birthday, userName, pwd));
			return true;
		} else {
			return false;
		}
	}

}
