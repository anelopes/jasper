<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
</head>
<body>

<form action="movimentacoes" method="POST">

	Tipo de movimentação: <br>
	<input type="radio" name="tipo_mov" value="SAIDA" checked="checked"> SAIDA <br>
	<input type="radio" name="tipo_mov" value="ENTRADA"> ENTRADA<br>

	<input type="submit" value="Gera PDF" />

</form>
</body>
</html>