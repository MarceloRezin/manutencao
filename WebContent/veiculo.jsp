<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" import="model.Veiculo, model.VeiculoTipo"%>

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
		<a class="active" href="/manutencao/veiculo">Veículos</a>
		<a href="/manutencao/manutencao">Itens</a>
		<a href="/manutencao/manutencao">Manutenções</a>
	</div>

	<div>
		<h2>${sessionScope.titulo}</h2>
	</div>

	<form action="/manutencao/veiculo/novo" method="post">
		<table align="center">
			<tr>
				<td align="right">Descrição:</td>
				<td align="left">
					<input required type="text" name="descricao" value="${sessionScope.veiculo.descricao}"/>
				</td>
			</tr>
			<tr>
				<td align="right">Placa:</td>
				<td align="left">
					<input required type="text" name="placa" value="${sessionScope.veiculo.placa}"/>
				</td>
			</tr>
			<tr>
				<td align="right">Ano:</td>
				<td align="left">
					<c:choose>
				 		<c:when test="${sessionScope.veiculo.ano == 0}">
							<input required type="number" name="ano"/>
					 	</c:when>
						<c:otherwise>
						   <input required type="number" name="ano" value="${sessionScope.veiculo.ano}"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td align="right">Tipo de Veículo:</td>
				<td align="left">
					<select name="tipo">
						<c:forEach var="vTipo" items="${sessionScope.tipos}">
			   				<option value="${vTipo}">${vTipo.descricao}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left">
					<input class="button buttongreen" type="submit" value="Salvar"/>
				</td>
			</tr>
		</table>
	</form>	
</body>
</html>