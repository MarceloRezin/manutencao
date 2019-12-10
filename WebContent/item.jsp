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
	<form action="${sessionScope.urlSave}" method="post">
		<input type="number" style="display: none" value="${sessionScope.item.id}" name="id" />
		<table align="center">
			<tr>
				<td align="right">Descrição:</td>
				<td align="left">
					<input required type="text" name="descricao" value="${sessionScope.item.descricao}"/>
				</td>
			</tr>
			<tr>
				<td align="left">
					<input class="button buttongreen" type="submit" value="Salvar"/>
				</td>
				<c:if test="${sessionScope.item.id != null}">
					<td align="left">
						<a href="/manutencao/item/delete?id=${sessionScope.item.id}" class='button buttonred'>Excluir</a>	
					</td>
				</c:if> 
			</tr>
		</table>
	</form>	
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