package dao;
import resources.Resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class DAOResources implements DAOInterface<Integer, Resource> {

	private TreeMap<Integer, Resource> resMap;
	
	public DAOResources() {
		resMap = new TreeMap<>();
	}

	public DAOResources(ArrayList<Resource> resourceList) {
		for(Resource r : resourceList) {
			resMap.put(r.getID(), r);
		}
	}
	
	@Override
	public ArrayList<Resource> listAll() {
		ArrayList<Resource> allResources = new ArrayList<Resource>();
		for (Iterator <Integer> it = resMap.keySet().iterator(); it.hasNext();) {
			allResources.add(resMap.get(it.next()));
		}
		return allResources;
	}

	@Override
	public boolean createRecord(Resource r) {
		if(resMap.containsKey(r.getID())){
			System.err.println("Error: reservation already exists!");
			return false;	
		} else {
			resMap.put(r.getID(), r);
			return true;
		}
	}

	@Override
	public boolean updateRecord(Resource r) {
		if(resMap.containsKey(r.getID())) {
			resMap.put(r.getID(), r);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteRecord(Resource r) {
		if(resMap.containsKey(r.getID())) {
			resMap.remove(r.getID());
			return true;
		} else {
			return false;
		}
	}

}
