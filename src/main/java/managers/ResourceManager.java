package managers;

import java.util.ArrayList;

import dao.DAO;
import resources.Resource;

public class ResourceManager<T extends Resource<?>> extends Manager<Integer, T > {

	public ResourceManager(DAO<Integer, T> dao) {
		super(dao);
	}

	public ArrayList<T> returnByConstrain(int param) {
		ArrayList<T> listResources = new ArrayList<>();
		ArrayList<T> allResources = getAllRecords();
		for (T resource : allResources) {
			if(resource.selectByConstratin(param)) {
				listResources.add(resource);
			}
		}
		return listResources;
	}
}
