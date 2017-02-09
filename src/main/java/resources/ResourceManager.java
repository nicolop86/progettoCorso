package resources;

import java.util.HashMap;

public class ResourceManager <T extends Resource> {
	
	private HashMap<Integer, T> resourceColl = new HashMap<>();

	public int addNewElement(T aResource) {
		resourceColl.put(aResource.getID(), aResource);
		return 1;
	}
	
	public T getElement(int ID) {
		return resourceColl.get(ID);
	}
	
	public int countElement() {
		return resourceColl.size();
	}
	
	
}