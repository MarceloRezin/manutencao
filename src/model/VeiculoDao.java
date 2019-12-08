package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import banco.Banco;
import model.Veiculo;
import model.VeiculoTipo;

public final class VeiculoDao implements Dao<Veiculo> {
	
	private static final String TABELA = "veiculos";
	
	public static VeiculoDao getInstance() {
		return new VeiculoDao();
	}

	@Override
	public Veiculo resultSetToModel(ResultSet rs) throws SQLException {
		return new Veiculo(
				rs.getInt("id"), 
				rs.getInt("ano"), 
				rs.getString("placa"), 
				VeiculoTipo.valueOf(rs.getString("tipo")), 
				rs.getString("descricao"));
	}

	@Override
	public Veiculo insert(final Veiculo model) throws ClassNotFoundException, SQLException {
		
		String sql = "INSERT INTO " + TABELA + " (ano, placa, tipo, descricao) VALUES(?,?,?,?)";
		
		Connection con = Banco.iniciarDb();
		PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		st.setInt(1, model.getAno());
		st.setString(2, model.getPlaca());
		st.setString(3, model.getTipo().toString());
		st.setString(4, model.getDescricao());
		
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
	public Veiculo update(final Veiculo model) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE " + TABELA + " SET ano = ?, placa = ?, tipo = ?, descricao = ? WHERE id = ?";
		
		Connection con = Banco.iniciarDb();
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, model.getAno());
		st.setString(2, model.getPlaca());
		st.setString(3, model.getTipo().toString());
		st.setString(4, model.getDescricao());
		st.setInt(5, model.getId());
		
		st.execute();
        
        st.close();
        con.close();
		
		return model;
	}

	@Override
	public Veiculo get(final int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from " + TABELA + " where id = " + id ;	

		Connection con = Banco.iniciarDb();
		Statement st = con.createStatement();
		
        ResultSet rs = st.executeQuery(sql);
        
        Veiculo veiculo = null;
        
        if(rs.next()) {
        	veiculo = resultSetToModel(rs);	
        }
        
        st.close();
        con.close();
            
        return veiculo;
	}

	@Override
	public List<Veiculo> list(final String query) throws ClassNotFoundException, SQLException {
		String where = "";
		
		if(query != null && query.trim().length() > 0) {
			where = " where descricao like '%" + query + "%'";
		}
		
		String sql = "select * from " + TABELA + where + " order by descricao" ;	

		Connection con = Banco.iniciarDb();
		Statement st = con.createStatement();
		
        ResultSet rs = st.executeQuery(sql);

        List<Veiculo> veiculos = new ArrayList<>();
        while (rs.next()){
            veiculos.add(resultSetToModel(rs));
        }
        
        st.close();
        con.close();
            
        return veiculos;
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
