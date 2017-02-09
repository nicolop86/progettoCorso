package testDAO;

import java.util.ArrayList;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import dao.DAO;
import users.*;

public class TestUsers {

	User u1;
	User u2;
	Administrator admin1;
	Administrator admin2;
	ArrayList<User> uList = new ArrayList<User>();
	
	@Before
	public void setUp() {
		u1 = new User("Nicolò", "Politi", "PLTNCL86H01M109C",
			new DateTime(1986, 6, 1, 0, 0).toDate(),"nicolo.politi", "pippo");
		admin1 = new Administrator("Paolo", "Verdi", "PLAVRD66B17E190R",
				new DateTime(1966, 5, 17, 0, 0).toDate(), "pverdi", "paperino");
	}

	@Test
	public void test() {
		DAO <String, User> daoUsers = new DAO<String, User>();
		Assert.assertEquals(true, daoUsers.createRecord(u1));
		u2 = new User("Luca", "Rossi", "LCARSS88H01B549V",
				new DateTime(1988, 6, 1, 0, 0).toDate(),"luca.rossi", "pippa");
		Assert.assertEquals(true, daoUsers.createRecord(u2));
		Assert.assertEquals(true, daoUsers.createRecord(admin1));
		Assert.assertEquals(false, daoUsers.createRecord(u2));

		admin2 = new Administrator("Paolo", "Verdi", "PLAVRD66B17E190R",
				new DateTime(1966, 5, 17, 0, 0).toDate(), "pverdi", "paperino");
		
		Assert.assertEquals(false, daoUsers.createRecord(admin2));
		admin1.setPwd("pluto");
		Assert.assertEquals(true, daoUsers.updateRecord(admin1));
		Assert.assertEquals(true, daoUsers.deleteRecord(u1));
		
		ArrayList<User> tempUsersList = daoUsers.getAll();
		System.out.println("============");
		for (User user : tempUsersList) {
			System.out.println(user.toString());
		}
	}

}
