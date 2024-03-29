<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" import="model.Manutencao, model.Veiculo, model.ManutencaoItem"%>

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
					<select name="veiculo_id" required>
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
	
	<br />

		
	<h2>Itens da Manutenção</h2>
	
	<a href='/manutencao/manutencao_item/novo?id=${sessionScope.manutencao.id}' class='button buttongreen'>Adicionar Itens</a>
	
	<c:choose>
		<c:when test="${!empty sessionScope.manutencao.itens}">
		 
		<table id="listagem" align="center">
			<tr>
				<th>Id</th>
				<th>Descrição</th>
				<th>Valor</th>
				<th></th>
			</tr>
			<c:forEach var="i" items="${sessionScope.manutencao.itens}">
		   		<tr>
					<td>${i.id}</td>
					<td>${i.item.descricao}</td>
					<td>${i.valorFormatado}</td>
					<td><a href="/manutencao/manutencao_item/delete?id=${i.id}&manutencao_id=${sessionScope.manutencao.id}">Remover</a></td>
				</tr>
			</c:forEach>
		</table>
		 
	 	</c:when>
		 <c:otherwise>
		   <br /><br />
		   <h3>Sem itens lançados.</h3>
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