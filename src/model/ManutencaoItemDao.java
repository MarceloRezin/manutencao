package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import banco.Banco;
import model.Item;

public final class ManutencaoItemDao implements Dao<ManutencaoItem>{
	
	private static final String TABELA = "manutencoes_itens";
	
	public static ManutencaoItemDao getInstance() {
		return new ManutencaoItemDao();
	}
	
	@Override
	public ManutencaoItem resultSetToModel(ResultSet rs) throws SQLException {		
		try {	
			return new ManutencaoItem(
					rs.getInt("id"),
					null, //Nulo pra não virar recursivo, é setado no contrutor da manutencao
					ItemDao.getInstance().get(rs.getInt("item_id")),
					rs.getBigDecimal("valor"));
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ManutencaoItem insert(final ManutencaoItem model) throws ClassNotFoundException, SQLException {
		
		String sql = "INSERT INTO " + TABELA + " (manutencao_id, item_id, valor) VALUES(?,?,?)";
		
		Connection con = Banco.iniciarDb();
		PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		st.setInt(1, model.getManutencao().getId());
		st.setInt(2, model.getItem().getId());
		st.setBigDecimal(3, model.getValor());

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
	public ManutencaoItem update(ManutencaoItem model) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE " + TABELA + " SET manutencao_id = ?, item_id = ?, valor = ? WHERE id = ?";
		
		Connection con = Banco.iniciarDb();
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, model.getManutencao().getId());
		st.setInt(2, model.getItem().getId());
		st.setBigDecimal(3, model.getValor());
		st.setInt(4, model.getId());
		
		st.execute();
        
        st.close();
        con.close();
		
		return model;
	}
	
	@Override
	public ManutencaoItem get(final int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from " + TABELA + " where id = " + id ;	

		Connection con = Banco.iniciarDb();
		Statement st = con.createStatement();
		
        ResultSet rs = st.executeQuery(sql);
        
        ManutencaoItem manutencaoItem = null;
        
        if(rs.next()) {
        	manutencaoItem = resultSetToModel(rs);	
        }
        
        st.close();
        con.close();
            
        return manutencaoItem;
	}
	
	@Override
	public List<ManutencaoItem> list(final String query) throws ClassNotFoundException, SQLException {
		String where = "";
		
		if(query != null && query.trim().length() > 0) {
			where = " where descricao like '%" + query + "%'";
		}
		
		String sql = "select * from " + TABELA + where;	

		Connection con = Banco.iniciarDb();
		Statement st = con.createStatement();
		
        ResultSet rs = st.executeQuery(sql);

        List<ManutencaoItem> manutencaoItens = new ArrayList<>();
        while (rs.next()){
        	manutencaoItens.add(resultSetToModel(rs));
        }
        
        st.close();
        con.close();
            
        return manutencaoItens;
	}
	
	public List<ManutencaoItem> listByManutencao(final int manutencaoId) throws ClassNotFoundException, SQLException {
		String sql = "select * from " + TABELA + " where manutencao_id = " + manutencaoId + " order by id";	

		Connection con = Banco.iniciarDb();
		Statement st = con.createStatement();
		
        ResultSet rs = st.executeQuery(sql);

        List<ManutencaoItem> manutencaoItens = new ArrayList<>();
        while (rs.next()){
        	manutencaoItens.add(resultSetToModel(rs));
        }
        
        st.close();
        con.close();
            
        return manutencaoItens;
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

	public void deleteByManutencao(final int manutencaoId) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM " + TABELA + " WHERE manutencao_id = " + manutencaoId;
		
		Connection con = Banco.iniciarDb();
		Statement st = con.createStatement();

		st.execute(sql);
        
        st.close();
        con.close();
	}
}
