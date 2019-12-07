package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Model;

public interface Dao<T extends Model> {
	
	public T resultSetToModel(ResultSet rs) throws SQLException;
	
	public T insert(T model);
	
	public T update(T model);

	public default T save(T model) {
		if(model.isNew()) {
			return insert(model);
		}else {
			return update(model);
		}
	}
		
	public T get(final int id);
	
	public List<T> list(final String query) throws ClassNotFoundException, SQLException;
	
	public void delete(final T model);
}
