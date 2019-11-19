package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import banco.Banco;
import model.Veiculo;
import model.VeiculoTipo;

public final class VeiculoService implements Service<Veiculo> {
	
	private static final VeiculoService INSTANCE = new VeiculoService();
	
	private  VeiculoService() {}
	
	public static VeiculoService getInstance() {
		return INSTANCE;
	}

	@Override
	public Veiculo get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Veiculo> list(String query) throws ClassNotFoundException, SQLException {
		
		String where = "";
		
		if(query != null && query.trim().length() > 0) {
			where = "where descricao like '%" + query + "%'";
		}
		
		String sql = "select * from veiculos " + where + " order by descricao" ;	

		Connection con = Banco.iniciarDb();
		Statement st = con.createStatement();
		
        ResultSet rs = st.executeQuery(sql);

        List<Veiculo> veiculos = new ArrayList<>();
        while (rs.next()){
            veiculos.add(
            		new Veiculo(
            				rs.getInt("id"), 
            				rs.getInt("ano"), 
            				rs.getString("placa"), 
            				VeiculoTipo.valueOf(rs.getString("tipo")), 
            				rs.getString("descricao")));
        }
        
        st.close();
        con.close();
            
        return veiculos;
        
	}

	@Override
	public Veiculo save(Veiculo model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Veiculo model) {
		// TODO Auto-generated method stub
		
	}

}
