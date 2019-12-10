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
import model.ManutencaoItem;
import model.ManutencaoItemDao;
import model.Veiculo;
import model.VeiculoDao;
import model.VeiculoTipo;

@WebServlet("/manutencao_item/*")
public class ManutencaoItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String id = request.getParameter("id");
			if(id == null || id.length() < 1) {
				throw new ManutencaoException("É necessário informar um ID.");
			}
			
			if(request.getRequestURI().endsWith("/novo")) { //NEW
				
				request.getSession().setAttribute("titulo", "Adicionar um Novo Item");
				request.getSession().setAttribute("itens", ItemDao.getInstance().list(null));
				request.getSession().setAttribute("manutencaoId", id);
				request.getSession().setAttribute("urlSave", "/manutencao/manutencao_item");
				request.getSession().setAttribute("urlVoltar", "/manutencao/manutencao?id=" + id);
				
				request.getRequestDispatcher("/manutencao_item.jsp").forward(request, response);
			}else if(request.getRequestURI().endsWith("/delete")){
				
				String manutencaoId = request.getParameter("manutencao_id");
				
				ManutencaoItemDao.getInstance().delete(Integer.parseInt(id));
				request.getSession().setAttribute("mensagem", "Item removido com sucesso!");
				response.sendRedirect("/manutencao/manutencao?id=" + manutencaoId);
			}else {
				throw new ManutencaoException("Caminho inválido.");
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
			
			String manutencaoId = request.getParameter("manutencaoId");
			
			ManutencaoItem manutencaoItem = new ManutencaoItem(
					null,
					ManutencaoDao.getInstance().get(Integer.parseInt(manutencaoId)),
					ItemDao.getInstance().get(Integer.parseInt(request.getParameter("item_id"))),
					new BigDecimal(request.getParameter("valor").replaceAll(",", "."))
					);
			
			
			ManutencaoItemDao.getInstance().save(manutencaoItem);
			request.getSession().setAttribute("mensagem", "Item adicionado sucesso!");
			
			response.sendRedirect("/manutencao/manutencao?id=" + manutencaoId);
				
		}catch(ClassNotFoundException | SQLException e1) {
			
			e1.printStackTrace();
			
			//TODO: Erro banco
		}catch(Exception e3) {
			
			e3.printStackTrace();
			
			//TODO: Erro não esperado
		}
	}

}
