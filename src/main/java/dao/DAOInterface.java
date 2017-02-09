package dao;

import java.util.List;

public interface DAOInterface <K, T> {
	
	List<T> listAll();
	boolean createRecord(T t);
	boolean updateRecord(T t);
	boolean deleteRecord(T t);
	
}