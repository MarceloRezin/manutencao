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
	<div id="snackbar">${sessionScope.mensagem}</div>
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

	<a href='/manutencao/item/novo' class='button buttongreen'>Novo Item</a><br /><br />
	
	<input type="text" placeholder="Buscar" id="busca" value="${sessionScope.busca}" /><a onclick="this.href='/manutencao/item?q='+document.getElementById('busca').value" href='' class='button buttonlightgreen'>Buscar</a>
	<c:choose>
 		<c:when test="${!empty sessionScope.itens}">
 		 
			<table id="listagem" align="center">
				<tr>
					<th>Id</th>
					<th>Descrição</th>
					<th></th>
				</tr>
				<c:forEach var="i" items="${sessionScope.itens}">
			   		<tr>
						<td>${i.id}</td>
						<td>${i.descricao}</td>
						<td><a href="?id=${i.id}">Editar</a></td>
					</tr>
				</c:forEach>
			</table>
	 </c:when>
		 <c:otherwise>
		   <br /><br />
		   <h3>Não foram encontrados itens.</h3>
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