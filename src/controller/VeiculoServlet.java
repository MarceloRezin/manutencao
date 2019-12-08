package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.ManutencaoException;
import model.Veiculo;
import model.VeiculoDao;
import model.VeiculoTipo;

@WebServlet("/veiculo")
public class VeiculoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			if(request.getRequestURI().endsWith("/novo")) { //NEW
				
			}else {
				
				String id = request.getParameter("id");
				boolean idInformado = id != null && id.length() > 0;
				
				if(request.getRequestURI().endsWith("/delete")){
					if(idInformado) { //DELETE
						VeiculoDao.getInstance().delete(Integer.parseInt(id));
					}else {
						throw new ManutencaoException("Não foi encontrado o veículo informado.");
					}
				}else {
					if(idInformado) { //GET
						Veiculo veiculo = VeiculoDao.getInstance().get(Integer.parseInt(id));
					}else { //LIST
						String query = request.getParameter("q");
						
						List<Veiculo> veiculos = VeiculoDao.getInstance().list(query);
						request.getSession().setAttribute("titulo", "Listagem de Veículos");
						request.getSession().setAttribute("busca", query);
						request.getSession().setAttribute("veiculos", veiculos);
						
						request.getRequestDispatcher("/veiculo-list.jsp").forward(request, response);
					}
				}
			}
				
		}catch(ClassNotFoundException | SQLException e1) {
			//TODO: Erro banco
		}catch(ManutencaoException e2) {
			//TODO: Erro normal
		}catch(Exception e3) {
			//TODO: Erro não esperado
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Veiculo veiculoSave;
			
			if(request.getRequestURI().endsWith("/new")) { //NEW
				veiculoSave = new Veiculo(null, Integer.parseInt(request.getParameter("ano")), request.getParameter("placa"), VeiculoTipo.valueOf(request.getParameter("tipo")), request.getParameter("descricao"));
			}else {
				String id = request.getParameter("id");
				
				if(id == null || id.length() < 1) {
					throw new ManutencaoException("Não foi encontrado o veículo informado.");
				}
				
				veiculoSave = VeiculoDao.getInstance().get(Integer.parseInt(id));

				veiculoSave.setAno(Integer.parseInt(request.getParameter("ano")));
				veiculoSave.setPlaca(request.getParameter("placa"));
				veiculoSave.setTipo(VeiculoTipo.valueOf(request.getParameter("tipo")));
				veiculoSave.setDescricao(request.getParameter("descricao"));
			}
			
			VeiculoDao.getInstance().save(veiculoSave);
				
		}catch(ClassNotFoundException | SQLException e1) {
			//TODO: Erro banco
		}catch(ManutencaoException e2) {
			//TODO: Erro normal
		}catch(Exception e3) {
			//TODO: Erro não esperado
		}
	}

}
