package managers;

import java.util.ArrayList;

import dao.DAO;
import resources.Resource;

public class ResourceManager<T extends Resource> extends Manager<Integer, T> {

	public ResourceManager(DAO<Integer,T> dao) {
		super(dao);
	}

	public ArrayList<T> returnByConstraint(int param) {
		ArrayList<T> listResources = new ArrayList<T>();
		ArrayList<T> allResources = getAllRecords();
		for (T resource : allResources) {
			if(resource.selectByConstraint(param)) {
				listResources.add(resource);
			}
		}
		return listResources;
	}
}