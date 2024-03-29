package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import banco.Banco;
import model.Item;

public final class ItemDao implements Dao<Item>{
	
	private static final String TABELA = "itens";
	
	public static ItemDao getInstance() {
		return new ItemDao();
	}
	
	@Override
	public Item resultSetToModel(ResultSet rs) throws SQLException {
		return new Item(
				rs.getInt("id"), 
				rs.getString("descricao"));
	}
	
	@Override
	public Item insert(final Item model) throws ClassNotFoundException, SQLException {
		
		String sql = "INSERT INTO " + TABELA + " (descricao) VALUES(?)";
		
		Connection con = Banco.iniciarDb();
		PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		st.setString(1, model.getDescricao());

		st.execute();
		
        ResultSet resultSet = st.executeQuery("SELECT LAST_INSERT_ID()");
        if(resultSet.next()) {
            model.setId(resultSet.getInt("LAST_INSERT_ID()"));
        }
        
        st.close();
        con.close();
		
		return model;
	}
	
	@Override
	public Item update(Item model) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE " + TABELA + " SET descricao = ? WHERE id = ?";
		
		Connection con = Banco.iniciarDb();
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, model.getDescricao());
		st.setInt(2, model.getId());
		
		st.execute();
        
        st.close();
        con.close();
		
		return model;
	}
	
	@Override
	public Item get(final int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from " + TABELA + " where id = " + id ;	

		Connection con = Banco.iniciarDb();
		Statement st = con.createStatement();
		
        ResultSet rs = st.executeQuery(sql);
        
        Item item = null;
        
        if(rs.next()) {
        	item = resultSetToModel(rs);	
        }
        
        st.close();
        con.close();
            
        return item;
	}
	
	@Override
	public List<Item> list(final String query) throws ClassNotFoundException, SQLException {
		String where = "";
		
		if(query != null && query.trim().length() > 0) {
			where = " where descricao like '%" + query + "%'";
		}
		
		String sql = "select * from " + TABELA + where + " order by descricao" ;	

		Connection con = Banco.iniciarDb();
		Statement st = con.createStatement();
		
        ResultSet rs = st.executeQuery(sql);

        List<Item> itens = new ArrayList<>();
        while (rs.next()){
            itens.add(resultSetToModel(rs));
        }
        
        st.close();
        con.close();
            
        return itens;
	}
	
	@Override
	public void delete(final int id) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM " + TABELA + " WHERE id = " + id;
		
		Connection con = Banco.iniciarDb();
		Statement st = con.createStatement();

		st.execute(sql);
        
        st.close();
        con.close();
	}
	
	public int count() throws SQLException, ClassNotFoundException {
		String sql = "SELECT COUNT(*) FROM " + TABELA;
		
		Connection con = Banco.iniciarDb();
		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery(sql);

        int count = 0;
        while (rs.next()){
            count = rs.getInt(1);
        }
        
        st.close();
        con.close();
        
        return count;
	}
}
