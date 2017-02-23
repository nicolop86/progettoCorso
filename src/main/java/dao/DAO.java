package dao;
import identifier.Identifiable;
import java.util.ArrayList;
import java.util.TreeMap;


public class DAO <K, T extends Identifiable<?>> {
	
	private TreeMap<K, T> tMap = new TreeMap<K, T>();
	
	public DAO () {
		
	}
	
	public DAO(ArrayList<T> aList) {
		for(T t : aList) {
			tMap.put((K) t.getID(), t);
		}
	}

	public void createRecord(T t) {
			tMap.put((K) t.getID(), t);
	}

	public void updateRecord(T t) {
			tMap.put((K) t.getID(), t);
	}

	public void deleteRecord(T t) {
		tMap.remove((K) t.getID());
		}
	
	public void deleteRecord(K ID) {
		tMap.remove(ID);
		}
	
	public TreeMap<K, T> getMap() {
		return this.tMap;
	}
	
}