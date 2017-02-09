package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import users.User;

public class DAOUsers implements DAOInterface<String, User> {

	private TreeMap<String, User> userMap;

	public DAOUsers() {
		userMap = new TreeMap<>();
	}

	public DAOUsers(ArrayList<User> userList) {
		for(User u : userList) {
			userMap.put(u.getID(), u);
		}
	}

	@Override
	public ArrayList<User> listAll() {
		ArrayList<User> allUsers= new ArrayList<User>();
		for (Iterator <String> it = userMap.keySet().iterator(); it.hasNext();) {
			allUsers.add(userMap.get(it.next()));
		}
		return allUsers;
	}

	@Override
	public boolean createRecord(User u) {
		if(userMap.containsKey(u.getID())){
			System.err.println("Error: user already exists!");
			return false;	
		} else {
			userMap.put(u.getID(), u);
			return true;
		}
	}

	@Override
	public boolean updateRecord(User u) {
		if(userMap.containsKey(u.getID())) {
			userMap.put(u.getID(), u);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteRecord(User u) {
		if(userMap.containsKey(u.getID())) {
			userMap.remove(u.getID());
			return true;
		} else {
			return false;
		}
	}

}
