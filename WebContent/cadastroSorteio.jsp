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
<form action="CadastroSorteioServlet" method="post">
	<div class="row">
		<div class="col-sm">
			<label>Data de Abertura</label>
			<input id="dataAbertura" type="text" name="dataAbertura"/>
		</div>
		<div class="col-sm">
			<label>Data de Encerramento</label>
			<input id="dataEncerramento" type="text" name="dataEncerramento"/>
		</div>
	</div>
	<div class="row">
		<div class="col-sm">
			<label>Valor da Aposta</label>
			<input id="valorAposta" type="text" name="valorAposta"/>
		</div>
		<div class="col-sm">
			<label>Quantidade de números possíveis</label>
			<input id="rangeNum" type="text" name="rangeNum"/>
		</div>
		<div class="col-sm">
			<label>Quantidade de números por Aposta</label>
			<input id="rangeAposta" type="text" name="rangeAposta"/>
		</div>
	</div>
	<button name=btn value="salvar">Salvar</button>
</form>
</div>
					
</body>
</html>