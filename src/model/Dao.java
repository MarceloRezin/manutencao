package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Model;

public interface Dao<T extends Model> {
	
	public T resultSetToModel(ResultSet rs) throws SQLException;
	
	public T insert(final T model) throws ClassNotFoundException, SQLException;
	
	public T update(final T model) throws ClassNotFoundException, SQLException;

	public default T save(final T model) throws ClassNotFoundException, SQLException{
		if(model.isNew()) {
			return insert(model);
		}else {
			return update(model);
		}
	}
		
	public T get(final int id) throws ClassNotFoundException, SQLException;
	
	public List<T> list(final String query) throws ClassNotFoundException, SQLException;
	
	public void delete(final int id) throws ClassNotFoundException, SQLException;
}
