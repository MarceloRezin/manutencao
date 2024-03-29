package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.ManutencaoException;
import model.Item;
import model.ItemDao;
import model.Manutencao;
import model.ManutencaoDao;
import model.Veiculo;
import model.VeiculoDao;
import model.VeiculoTipo;

@WebServlet("/manutencao/*")
public class ManutencaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getRequestURI().endsWith("/novo")) { //NEW
				request.getSession().setAttribute("titulo", "Cadastrar Nova Manutenção");
				request.getSession().setAttribute("manutencao", new Manutencao(LocalDateTime.now()));
				request.getSession().setAttribute("veiculos", VeiculoDao.getInstance().list(null));
				request.getSession().setAttribute("urlSave", "/manutencao/manutencao/novo");
				
				request.getRequestDispatcher("/manutencao.jsp").forward(request, response);
			}else {
				String id = request.getParameter("id");
				boolean idInformado = id != null && id.length() > 0;
				
				if(request.getRequestURI().endsWith("/delete")){
					if(idInformado) { //DELETE
						ManutencaoDao.getInstance().delete(Integer.parseInt(id));
						request.getSession().setAttribute("mensagem", "Excluído com sucesso!");
						response.sendRedirect("/manutencao/manutencao");
					}else {
						throw new ManutencaoException("Não foi encontrado a manutencao infomarda.");
					}
				}else {
					if(idInformado) { //GET
						Manutencao manutencao = ManutencaoDao.getInstance().get(Integer.parseInt(id));
						
						request.getSession().setAttribute("titulo", "Editar Manutenção");
						request.getSession().setAttribute("manutencao", manutencao);
						request.getSession().setAttribute("veiculos", VeiculoDao.getInstance().list(null));
						request.getSession().setAttribute("urlSave", "/manutencao/manutencao");
			
						request.getRequestDispatcher("/manutencao.jsp").forward(request, response);
						request.getSession().setAttribute("mensagem", null);
					}else { //LIST	
						String query = request.getParameter("q");
						
						List<Manutencao> manutencoes = ManutencaoDao.getInstance().list(query);
						request.getSession().setAttribute("titulo", "Listagem de Manutenções");
						request.getSession().setAttribute("busca", query);
						request.getSession().setAttribute("manutencoes", manutencoes);
						
						request.getRequestDispatcher("/manutencao-list.jsp").forward(request, response);
						request.getSession().setAttribute("mensagem", null);
					}
				}
			}
				
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Manutencao manutencaoSave;
			
			if(request.getRequestURI().endsWith("/novo")) { //NEW
				manutencaoSave = new Manutencao(
						null,
						LocalDateTime.now(), 
						new BigDecimal(request.getParameter("quilometragem").replaceAll(",", ".")), 
						request.getParameter("descricao"), 
						new BigDecimal(request.getParameter("valor").replaceAll(",", ".")), 
						VeiculoDao.getInstance().get(Integer.parseInt(request.getParameter("veiculo_id"))),
						Collections.emptyList()
						);
			}else {
				
				String id = request.getParameter("id");
				
				if(id == null || id.length() < 1) {
					throw new ManutencaoException("Não foi encontrado o veículo informado.");
				}
				
				manutencaoSave = ManutencaoDao.getInstance().get(Integer.parseInt(id));
				manutencaoSave.setQuilometragem(new BigDecimal(request.getParameter("quilometragem").replaceAll(",", ".")));
				manutencaoSave.setDescricao(request.getParameter("descricao"));
				manutencaoSave.setValor(new BigDecimal(request.getParameter("valor").replaceAll(",", ".")));
				manutencaoSave.setVeiculo(VeiculoDao.getInstance().get(Integer.parseInt(request.getParameter("veiculo_id"))));
			}
			
			ManutencaoDao.getInstance().save(manutencaoSave);
			request.getSession().setAttribute("mensagem", "Salvo com sucesso!");
			response.sendRedirect("/manutencao/manutencao");
				
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
