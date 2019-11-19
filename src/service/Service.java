package service;

import java.sql.SQLException;
import java.util.List;

import model.Model;

public interface Service<T extends Model> {
		
	public T get(int id);
	
	public List<T> list(String query) throws ClassNotFoundException, SQLException;
	
	public T save(T model);
	
	public void delete(T model);
}
