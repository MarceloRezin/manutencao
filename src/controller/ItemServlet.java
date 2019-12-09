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
import model.Item;
import model.ItemDao;

@WebServlet("/item/*")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			if(request.getRequestURI().endsWith("/novo")) { //NEW
				request.getSession().setAttribute("titulo", "Cadastrar Novo Item");
				request.getSession().setAttribute("item", new Item());
				request.getSession().setAttribute("urlSave", "/manutencao/item/novo");
				
				request.getRequestDispatcher("/item.jsp").forward(request, response);
			}else {
				String id = request.getParameter("id");
				boolean idInformado = id != null && id.length() > 0;
				
				if(request.getRequestURI().endsWith("/delete")){
					if(idInformado) { //DELETE
						ItemDao.getInstance().delete(Integer.parseInt(id));
						request.getSession().setAttribute("mensagem", "Excluído com sucesso!");
						response.sendRedirect("/manutencao/item");
					}else {
						throw new ManutencaoException("Não foi encontrado o veículo informado.");
					}
				}else {
					if(idInformado) { //GET
						Item item = ItemDao.getInstance().get(Integer.parseInt(id));
						
						request.getSession().setAttribute("titulo", "Editar Item");
						request.getSession().setAttribute("item", item);
						request.getSession().setAttribute("urlSave", "/manutencao/item");
			
						request.getRequestDispatcher("/item.jsp").forward(request, response);
					}else { //LIST
						String query = request.getParameter("q");
						
						List<Item> itens = ItemDao.getInstance().list(query);
						request.getSession().setAttribute("titulo", "Listagem de Itens");
						request.getSession().setAttribute("busca", query);
						request.getSession().setAttribute("itens", itens);
						
						request.getRequestDispatcher("/item-list.jsp").forward(request, response);
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
			Item itemSave;
			
			if(request.getRequestURI().endsWith("/novo")) { //NEW
				itemSave = new Item(null, request.getParameter("descricao"));
			}else {
				String id = request.getParameter("id");
				
				if(id == null || id.length() < 1) {
					throw new ManutencaoException("Não foi encontrado o veículo informado.");
				}
				
				itemSave = ItemDao.getInstance().get(Integer.parseInt(id));
				itemSave.setDescricao(request.getParameter("descricao"));
			}
			
			ItemDao.getInstance().save(itemSave);
			request.getSession().setAttribute("mensagem", "Salvo com sucesso!");
			response.sendRedirect("/manutencao/item");
				
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
