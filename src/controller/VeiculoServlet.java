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

@WebServlet("/veiculo/*")
public class VeiculoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			if(request.getRequestURI().endsWith("/novo")) { //NEW
				request.getSession().setAttribute("titulo", "Cadastrar Novo Veículo");
				request.getSession().setAttribute("veiculo", new Veiculo());
				request.getSession().setAttribute("tipos", VeiculoTipo.values());
				request.getSession().setAttribute("urlSave", "/manutencao/veiculo/novo");
				
				request.getRequestDispatcher("/veiculo.jsp").forward(request, response);
			}else {
				String id = request.getParameter("id");
				boolean idInformado = id != null && id.length() > 0;
				
				if(request.getRequestURI().endsWith("/delete")){
					if(idInformado) { //DELETE
						VeiculoDao.getInstance().delete(Integer.parseInt(id));
						request.getSession().setAttribute("mensagem", "Excluído com sucesso!");
						response.sendRedirect("/manutencao/veiculo");
					}else {
						throw new ManutencaoException("Não foi encontrado o veículo informado.");
					}
				}else {
					if(idInformado) { //GET
						Veiculo veiculo = VeiculoDao.getInstance().get(Integer.parseInt(id));
						
						request.getSession().setAttribute("titulo", "Editar Veículo");
						request.getSession().setAttribute("veiculo", veiculo);
						request.getSession().setAttribute("tipos", VeiculoTipo.values());
						request.getSession().setAttribute("urlSave", "/manutencao/veiculo");
			
						request.getRequestDispatcher("/veiculo.jsp").forward(request, response);
					}else { //LIST
						String query = request.getParameter("q");
						
						List<Veiculo> veiculos = VeiculoDao.getInstance().list(query);
						request.getSession().setAttribute("titulo", "Listagem de Veículos");
						request.getSession().setAttribute("busca", query);
						request.getSession().setAttribute("veiculos", veiculos);
						
						request.getRequestDispatcher("/veiculo-list.jsp").forward(request, response);
						request.getSession().setAttribute("mensagem", null);
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
			
			if(request.getRequestURI().endsWith("/novo")) { //NEW
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
			request.getSession().setAttribute("mensagem", "Salvo com sucesso!");
			response.sendRedirect("/manutencao/veiculo");
				
		}catch(ClassNotFoundException | SQLException e1) {
			
			e1.printStackTrace();
			
			//TODO: Erro banco
		}catch(ManutencaoException e2) {
			e2.printStackTrace();
			
			//TODO: Erro normal
		}catch(Exception e3) {
			
			e3.printStackTrace();
			
			//TODO: Erro não esperado
		}
	}

}
