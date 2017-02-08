package resources;

import java.util.Date;
import java.util.Hashtable;
//import org.joda.time.DateTime;
import users.User;

public class ResourceManager <T extends Resource> {
	
	private Hashtable<Integer, T> resourceColl = new Hashtable<>();

	public int addNewElement(T aResource) {
		resourceColl.put(aResource.getID(), aResource);
		return 1;
	}
	
	public T getElement(int ID) {
		return resourceColl.get(ID);
	}
	
	public int rentResource(int ID, User user, Date dateStart, Date dateFinish) {
		T aResource = resourceColl.get(ID);
		if(aResource!=null && aResource.isAvailable()) {
			aResource.setAvailable(false);
			aResource.setUser(user);
			aResource.setDateStart(dateStart);
			aResource.setDateFinish(dateFinish);
			return 1;
		} else {
			return -1;
		}
	}
}