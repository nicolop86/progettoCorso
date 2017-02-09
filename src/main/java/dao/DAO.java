package dao;
import identifier.Identifiable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
@SuppressWarnings("rawtypes")

public class DAO <K, T extends Identifiable> {
	
	private TreeMap<K, T> tMap = new TreeMap<K, T>();
	
	public DAO () {
		
	}
	
	public DAO(ArrayList<T> aList) {
		for(T t : aList) {
			tMap.put((K) t.getID(), t);
		}
	}
	
	public ArrayList<T> getAll() {
		ArrayList<T> allResources = new ArrayList<T>();
		for (Iterator <K> it = tMap.keySet().iterator(); it.hasNext();) {
			allResources.add(tMap.get(it.next()));
		}
		return allResources;
	}

	public boolean createRecord(T t) {
		if(tMap.containsKey((K) t.getID())){
			System.err.println("Error: ID already exists!");
			return false;	
		} else {
			tMap.put((K) t.getID(), t);
			return true;
		}
	}

	public boolean updateRecord(T t) {
		if(tMap.containsKey((K) t.getID())) {
			tMap.put((K) t.getID(), t);
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteRecord(T t) {
		if(tMap.containsKey((K) t.getID())) {
			tMap.remove((K) t.getID());
			return true;
		} else {
			return false;
		}
	}
	
}