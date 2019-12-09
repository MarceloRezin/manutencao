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
	<div id="snackbar">${sessionScope.mensagem}</div>
	<div class="topnav"> 
		<a style="float: left; margin-right: 20px; font-weight: bold; font-size: 19px; border-right: 1px solid white">Gerenciamento de Manutenções</a>
		<a href="/manutencao/">Home</a>
		<a href="/manutencao/veiculo">Veículos</a>
		<a href="/manutencao/item">Itens</a>
		<a class="active"href="/manutencao/manutencao">Manutenções</a>
	</div>

	<div>
		<h2>${sessionScope.titulo}</h2>
	</div>

	<a href='/manutencao/manutencao/novo' class='button buttongreen'>Nova Manutenção</a><br /><br />
	
	<input type="text" placeholder="Buscar" id="busca" value="${sessionScope.busca}" /><a onclick="this.href='/manutencao/manutencao?q='+document.getElementById('busca').value" href='' class='button buttonlightgreen'>Buscar</a>
	<c:choose>
 		<c:when test="${!empty sessionScope.manutencoes}">
 		 
			<table id="listagem" align="center">
				<tr>
					<th>Id</th>
					<th>Quilometragem</th>
					<th>Descrição</th>
					<th>Valor</th>
					<th>Veículo</th>
					<th>Qtd. Itens</th>
					<th></th>
				</tr>
				<c:forEach var="m" items="${sessionScope.manutencoes}">
			   		<tr>
						<td>${m.id}</td>
						<td>${m.quilometragemFormatada}</td>
						<td>${m.descricao}</td>
						<td>${m.valorFormatado}</td>
						<td>${m.veiculo.descricao}</td>
						<td>${m.quantidadeItens}</td>
						<td><a href="?id=${m.id}">Editar</a></td>
					</tr>
				</c:forEach>
			</table>
		 
	 </c:when>
		 <c:otherwise>
		   <br /><br />
		   <h3>Não foram encontradas manutenções.</h3>
		 </c:otherwise>
	</c:choose>
	
	<script>
		function showToast() {
		  var x = document.getElementById("snackbar");
		  x.className = "show";
		  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
		}
	</script>
	
	<c:if test="${sessionScope.mensagem != null}">
		<script>
			showToast();
		</script>
	</c:if> 
</body>
</html>