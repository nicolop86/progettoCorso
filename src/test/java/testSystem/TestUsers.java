package testSystem;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DAO;
import managers.UserManager;
import users.Administrator;
import users.User;

public class TestUsers {

	User u1;
	User u2;
	UserManager uManager;
	Administrator admin1;
	Administrator admin2;
	DAO <String, User> daoUsers;
	ArrayList<User> anArrayOfUsers = new ArrayList<>();

	@Before
	public void setup() {
	daoUsers = new DAO<String, User>();
	uManager = new UserManager(daoUsers);
	u1 = new User("Nicolò", "Politi", "PLTNCL86H01M109C",
			new DateTime(1986, 6, 1, 0, 0).toDate(),"nicolo.politi", "pippo");
	admin1 = new Administrator("Paolo", "Verdi", "PLAVRD66B17E190R",
				new DateTime(1966, 5, 17, 0, 0).toDate(), "pverdi", "paperino");
	u2 = new User("Luca", "Rossi", "LCARSS88H01B549V",
			new DateTime(1988, 6, 1, 0, 0).toDate(),"luca.rossi", "pippa");
	admin2 = new Administrator("Giovanni", "Blu", "GVNBLU66B17E190R",
			new DateTime(1966, 5, 17, 0, 0).toDate(), "gblu", "pippa");
	anArrayOfUsers.add(u1);
	anArrayOfUsers.add(u2);
	}
	
	@Test
	public void test() {
		
		
		/*Tests on user u1 and u2*/
		Assert.assertEquals(true, uManager.createRecord(u1));
		Assert.assertEquals(false, uManager.createRecord(u1));
		Assert.assertEquals(true, uManager.login(u1));
		Assert.assertEquals(false, uManager.login(u1));
		Assert.assertEquals(true, uManager.logout(u1));
		Assert.assertEquals(false, uManager.logout(u1));
		Assert.assertEquals(u1, uManager.getElement("PLTNCL86H01M109C"));
		Assert.assertEquals(true, uManager.updateRecord(new User("Niccolò",
				"Politi", "PLTNCL86H01M109C", new DateTime(1986, 6, 1, 0, 0).toDate(),
				"nicolo.politi", "pippo")));
		Assert.assertEquals(false, uManager.updateRecord(u2));
		Assert.assertEquals(true, uManager.createRecord(u2));
		Assert.assertEquals(false, uManager.createRecord(u2));
		//Assert.assertEquals(anArrayOfUsers, uManager.getAllRecords());
		Assert.assertEquals(true, uManager.deleteRecord(u1));
		Assert.assertEquals(true, uManager.deleteRecord(u2));
		Assert.assertEquals(false, uManager.deleteRecord(u1));
		
		/*Tests on user admin1*/
		Assert.assertEquals(true, uManager.createRecord(admin1));
		Assert.assertEquals(false, uManager.createRecord(admin1));
		Assert.assertEquals(true, admin1.isAdmin());
		Assert.assertEquals(true, uManager.login(admin1));
		Assert.assertEquals(false, uManager.login(admin1));
		Assert.assertEquals(true, uManager.logout(admin1));
		Assert.assertEquals(false, uManager.logout(admin1));
		Assert.assertEquals(true, uManager.createRecord(admin2));
		Assert.assertEquals(admin2, uManager.getElement("GVNBLU66B17E190R"));
		Assert.assertEquals(true, uManager.login(admin2));
		Assert.assertEquals(true, uManager.logout(admin2));
		Assert.assertEquals("Name: Giovanni\nSurname: Blu\n"
				+ "Identifier: GVNBLU66B17E190R\nBirth date: 17-05-1966", admin2.toString());
		
	}

}
