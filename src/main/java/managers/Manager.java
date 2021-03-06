package managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import dao.DAO;
import identifier.Identifiable;

public class Manager <K, T extends Identifiable<K>> {

	protected DAO<K, T> dao;
	
	public Manager(DAO<K, T> dao) {
		this.dao = dao;
	}

	public boolean createRecord(T t) {
		TreeMap<K, T> tMap = dao.getMap();
		if(tMap.containsKey(t.getID())){
			System.err.println("Error: ID already exists!\n");
			return false;	
		} else {
			dao.createRecord(t);
			return true;
		}
	}

	public boolean deleteRecord(T t) {
		TreeMap<K, T> tMap = dao.getMap();
		if(tMap.containsKey(t.getID())) {
			dao.deleteRecord(t);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteRecord(K ID) {
		if(dao.getMap().containsKey(ID)) {
			dao.deleteRecord(ID);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateRecord(T t){
		TreeMap<K, T> tMap = dao.getMap();
		if(tMap.containsKey(t.getID())) {
			dao.updateRecord(t);
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<T> getAllRecords() {
		TreeMap<K, T> tMap = dao.getMap();
		ArrayList<T> allResources = new ArrayList<T>();
		for (Iterator <K> it = tMap.keySet().iterator(); it.hasNext();) {
			allResources.add(tMap.get(it.next()));
		}
		return allResources;
	}
	
	public T getElement(K ID) {
		TreeMap<K, T> tMap = dao.getMap();
		if(tMap.containsKey(ID)) {
			return tMap.get(ID);
		} else {
			return null;
		}
	}

}
