<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./CSS/style.css">
<title>SorteioES</title>
</head>
<body>
<div class="center-div">
<form action="CadastroServlet" method="post">
	<label>Nome</label>
	<input type="text" name="nome"/>
	<label>CPF</label>
	<input type="text" name="cpf"/>
	<label>Email</label>
	<input type="text" name=email/>
	<label>Senha</label>
	<input type="password" name="senha"/>
	<button name=btn value="cadastrar">Salvar</button>
</form>
</div>
					
</body>
</html>