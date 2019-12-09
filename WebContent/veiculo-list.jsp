<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" import="model.Veiculo"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manutenção</title>
<link rel="stylesheet" href="styles.css">
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

	<a href='/manutencao/veiculo/novo' class='button buttongreen'>Novo Veículo</a><br /><br />
	
	<input type="text" placeholder="Buscar" id="busca" value="${sessionScope.busca}" /><a onclick="this.href='/manutencao/veiculo?q='+document.getElementById('busca').value" href='' class='button buttonlightgreen'>Buscar</a>
	<c:choose>
 		<c:when test="${!empty sessionScope.veiculos}">
 		 
			<table id="listagem" align="center">
				<tr>
					<th>Id</th>
					<th>Descrição</th>
					<th>Placa</th>
					<th>Tipo</th>
					<th>Ano</th>
					<th></th>
				</tr>
				<c:forEach var="v" items="${sessionScope.veiculos}">
			   		<tr>
						<td>${v.id}</td>
						<td>${v.descricao}</td>
						<td>${v.placa}</td>
						<td>${v.tipo.descricao}</td>
						<td>${v.ano}</td>
						<td><a href="?id=${v.id}">Editar</a></td>
					</tr>
				</c:forEach>
			</table>
		 
	 </c:when>
		 <c:otherwise>
		   <br /><br />
		   <h3>Não foram encontrados veículos.</h3>
		 </c:otherwise>
	</c:choose>
</body>
</html>