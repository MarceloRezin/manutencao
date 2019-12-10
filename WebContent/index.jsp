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
		<a class="active" href="/manutencao/">Home</a>
		<a href="/manutencao/veiculo">Veículos</a>
		<a href="/manutencao/item">Itens</a>
		<a href="/manutencao/manutencao">Manutenções</a>
	</div>
	<h1>Bem vindo ao registro de manutenções!</h1>
	<h2>Relatório das informações:</h2>
	<table id="listagem" align="center">
		<tr>
			<th>Veículos</th>
			<th>Itens</th>
			<th>Manutenções</th>
		</tr>
   		<tr>
			<td>${sessionScope.countVeiculo}</td>
			<td>${sessionScope.countItem}</td>
			<td>${sessionScope.countManutencao}</td>
		</tr>
	</table>
</body>
</html>