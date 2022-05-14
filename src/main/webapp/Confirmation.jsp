<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html"; charset="ISO-8859-1">
<title>Confirmation</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<%@include file="header.jsp" %>
	<div class="container col-md-10 col-md-offset-1">
	<div class="panel panel-primary">
		<div class="panel-heading">Confirmation</div>
		<div class="panel-body">
		<div class="form-group">
		<label>ID: </label>
		<label>${produit.id}</label>
		</div>
		<div class="form-group">
		<label>D�signation: </label>
		<label>${produit.designation}</label>
		</div>
		<div class="form-group">
		<label>Prix: </label>
		<label>${produit.prix}</label>
		</div>
		<div class="form-group">
		<label>Quantite: </label>
		<label>${produit.quantite}</label>
		</div>
	</div>
	</div>
	</div>
</body>
</html>