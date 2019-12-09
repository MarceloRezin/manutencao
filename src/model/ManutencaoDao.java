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

public final class ManutencaoDao implements Dao<Manutencao> {
	
	private static final String TABELA = "manutencoes";
	
	public static ManutencaoDao getInstance() {
		return new ManutencaoDao();
	}

	@Override
	public Manutencao resultSetToModel(ResultSet rs) throws SQLException {
		try {
			return new Manutencao(
					rs.getInt("id"), 
					Timestamp.valueOf(rs.getString("data_hora")).toLocalDateTime(), 
					rs.getBigDecimal("quilometragem"), 
					rs.getString("descricao"), 
					rs.getBigDecimal("valor"),
					VeiculoDao.getInstance().get(rs.getInt("veiculo_id")),
					new ArrayList<Object>());
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Manutencao insert(final Manutencao model) throws ClassNotFoundException, SQLException {
		
		String sql = "INSERT INTO " + TABELA + " (data_hora, quilometragem, descricao, valor, veiculo_id) VALUES(?,?,?,?,?)";
		
		Connection con = Banco.iniciarDb();
		PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		st.setTimestamp(1, Timestamp.valueOf(model.getDataHora()));
		st.setBigDecimal(2, model.getQuilometragem());
		st.setString(3, model.getDescricao());
		st.setBigDecimal(4, model.getValor());
		st.setInt(5, model.getVeiculo().getId());
		
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
	public Manutencao update(final Manutencao model) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE " + TABELA + " SET data_hora = ?, quilometragem = ?, descricao = ?, valor = ?, veiculo_id = ? WHERE id = ?";
		
		Connection con = Banco.iniciarDb();
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setTimestamp(1, Timestamp.valueOf(model.getDataHora()));
		st.setBigDecimal(2, model.getQuilometragem());
		st.setString(3, model.getDescricao());
		st.setBigDecimal(4, model.getValor());
		st.setInt(5, model.getVeiculo().getId());
		st.setInt(6, model.getId());
		
		st.execute();
        
        st.close();
        con.close();
		
		return model;
	}

	@Override
	public Manutencao get(final int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from " + TABELA + " where id = " + id ;	

		Connection con = Banco.iniciarDb();
		Statement st = con.createStatement();
		
        ResultSet rs = st.executeQuery(sql);
        
        Manutencao manutencao = null;
        
        if(rs.next()) {
        	manutencao = resultSetToModel(rs);	
        }
        
        st.close();
        con.close();
            
        return manutencao;
	}

	@Override
	public List<Manutencao> list(final String query) throws ClassNotFoundException, SQLException {
		String where = "";
		
		if(query != null && query.trim().length() > 0) {
			where = " where descricao like '%" + query + "%'";
		}
		
		String sql = "select * from " + TABELA + where + " order by data_hora desc";	

		Connection con = Banco.iniciarDb();
		Statement st = con.createStatement();
		
        ResultSet rs = st.executeQuery(sql);

        List<Manutencao> manutencoes = new ArrayList<>();
        while (rs.next()){
            manutencoes.add(resultSetToModel(rs));
        }
        
        st.close();
        con.close();
            
        return manutencoes;
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

	
}