package testDAO;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import dao.DAO;
import managers.UserManager;
import users.*;

public class TestUsers {

	User u1;
	User u2;
	UserManager uManager;
	Administrator admin1;
	Administrator admin2;
	DAO <String, User> daoUsers;

	@Test
	public void test() {
		daoUsers = new DAO<String, User>();
		uManager = new UserManager(daoUsers);
		u1 = new User("Nicolò", "Politi", "PLTNCL86H01M109C",
				new DateTime(1986, 6, 1, 0, 0).toDate(),"nicolo.politi", "pippo", uManager);
		admin1 = new Administrator("Paolo", "Verdi", "PLAVRD66B17E190R",
					new DateTime(1966, 5, 17, 0, 0).toDate(), "pverdi", "paperino", uManager);
		u2 = new User("Luca", "Rossi", "LCARSS88H01B549V",
				new DateTime(1988, 6, 1, 0, 0).toDate(),"luca.rossi", "pippa", uManager);
		admin2 = new Administrator("Giovanni", "Blu", "GVNBLU66B17E190R",
				new DateTime(1966, 5, 17, 0, 0).toDate(), "gblu", "pippa", uManager);
		
		/*Tests on user u1*/
		Assert.assertEquals(true, u1.register());
		Assert.assertEquals(false, u1.register());
		Assert.assertEquals(true, u1.login());
		Assert.assertEquals(false, u1.login());
		Assert.assertEquals(true, u1.isLogged());
		Assert.assertEquals(true, u1.logout());
		Assert.assertEquals(false, u1.isLogged());
		
		/*Tests on user admin1*/
		Assert.assertEquals(true, admin1.register());
		Assert.assertEquals(false, admin1.register());
		Assert.assertEquals(true, admin1.login());
		Assert.assertEquals(false, admin1.login());
		Assert.assertEquals(true, admin1.isLogged());
		u1.setName("Niccolò");
		Assert.assertEquals(true, admin1.updateUser(u1));
		System.out.println(u1.toString());
		Assert.assertEquals(true, admin1.logout());
		Assert.assertEquals(false, admin1.isLogged());
		
		/*Tests on user admin2*/
		Assert.assertEquals(true,admin2.register());
		Assert.assertEquals(true, admin2.login());
		Assert.assertEquals(true, u2.register());
		Assert.assertEquals(u2, uManager.getElement("LCARSS88H01B549V"));
		Assert.assertEquals(true, admin2.deleteUser(u2));
		
	}

}
