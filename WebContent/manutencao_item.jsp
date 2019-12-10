<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" import="model.Item"%>

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
		<a class="active" href="/manutencao/item">Itens</a>
		<a href="/manutencao/manutencao">Manutenções</a>
	</div>
	<div>
		<h2>${sessionScope.titulo}</h2>
	</div>
	<form action="${sessionScope.urlSave}" method="post">
		<input type="number" style="display: none" value="${sessionScope.manutencaoId}" name="manutencaoId" />
		<table align="center">
			<tr>
				<td align="right">Item:</td>
				<td align="left">
					<select name="item_id" value>
						<c:forEach var="it" items="${sessionScope.itens}">
						   <option value="${it.id}">${it.descricao}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">Valor (R$):</td>
				<td align="left">
					<input required type="number" step="0.01" name="valor"/>
				</td>
			</tr>
			<tr>
				<td align="left">
					<input class="button buttongreen" type="submit" value="Adicionar"/>
				</td>
				<td align="left">
					<a href="${sessionScope.urlVoltar}" class='button buttonred'>Cancelar</a>	
				</td>
			</tr>
		</table>
	</form>	
</body>
</html>