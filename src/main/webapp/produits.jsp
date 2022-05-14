<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html"; charset="ISO-8859-1">
<title>Produits</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<%@include file="header.jsp" %>
	<div class="container col-md-10 col-md-offset-1">
	<div class="panel panel-primary">
		<div class="panel-heading">Recherche des produits</div>
		<div class="panel-body">
		<form action="chercher.do" method="get">
		<label>Mot clé</label>
		<input type="text" name="motCle" value="${model.motCle}"></input>
		<button type="submit" class="btn btn-primary">chercher</button>
		</form>
		<table class="table table-striped">
		<tr>
		<th>ID</th>
		<th>Désignation</th>
		<th>Prix</th>
		<th>Quantité</th>
		</tr>
		<c:forEach items="${model.produits }" var="p">
		<tr>
		<td>${p.id }</td>
		<td>${p.designation }</td>
		<td>${p.prix }</td>
		<td>${p.quantite }</td>
		</tr>
		</c:forEach>
		</table>
		</div>
	</div>
	</div>
</body>
</html>