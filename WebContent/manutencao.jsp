<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" import="model.Manutencao, model.Veiculo"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manutenção</title>
<link rel="stylesheet" href="/manutencao/styles.css">
</head>
<body style="text-align: center">
	<div class="topnav"> 
		<a style="float: left; margin-right: 20px; font-weight: bold; font-size: 19px; border-right: 1px solid white">Gerenciamento de Manutenções</a>
		<a href="/manutencao/">Home</a>
		<a href="/manutencao/veiculo">Veículos</a>
		<a href="/manutencao/item">Itens</a>
		<a class="active" href="/manutencao/manutencao">Manutenções</a>
	</div>
	<div>
		<h2>${sessionScope.titulo}</h2>
	</div>
	<form action="${sessionScope.urlSave}" method="post">
		<input type="number" style="display: none" value="${sessionScope.manutencao.id}" name="id" />
		
		<table align="center">
		
			<c:if test="${sessionScope.manutencao.id != null}">
				<tr>
					<td align="right">Data Hora:</td>
					<td align="left">
						<input type="text" value="${sessionScope.manutencao.dataHoraFormatada}"  disabled />
					</td>
				</tr>
			</c:if> 
			<tr>
				<td align="right">Quilometragem (km):</td>
				<td align="left">
					<input required type="number" step="0.01" name="quilometragem" value="${sessionScope.manutencao.quilometragem}"/>
				</td>
			</tr>
			<tr>
				<td align="right">Descrição:</td>
				<td align="left">
					<input required type="text" name="descricao" value="${sessionScope.manutencao.descricao}"/>
				</td>
			</tr>
			<tr>
				<td align="right">Valor (R$):</td>
				<td align="left">
					<input required type="number" step="0.01" name="valor" value="${sessionScope.manutencao.valor}"/>
				</td>
			</tr>
			<tr>
				<td align="right">Veículo:</td>
				<td align="left">
					<select name="veiculo_id" value>
						<c:forEach var="vei" items="${sessionScope.veiculos}">
							<c:choose>
						 		<c:when test="${sessionScope.manutencao.veiculo.id == vei.id}">
									<option value="${vei.id}" selected="selected">${vei.descricao}</option>
							 	</c:when>
								<c:otherwise>
								   <option value="${vei.id}">${vei.descricao}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="left">
					<input class="button buttongreen" type="submit" value="Salvar"/>
				</td>
				<c:if test="${sessionScope.manutencao.id != null}">
					<td align="left">
						<a href="/manutencao/manutencao/delete?id=${sessionScope.manutencao.id}" class='button buttonred'>Excluir</a>	
					</td>
				</c:if> 
			</tr>
		</table>
	</form>	
</body>
</html>