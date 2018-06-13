<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./CSS/style.css">

<script>
$(document).ready(function ()  {
	var params = (new URL(document.location)).searchParams;
	var number = params.get("numPorAposta");			
	var container = document.getElementById("numeros");			        
	while (container.hasChildNodes()) {
		container.removeChild(container.lastChild);
	}
	for (i=0;i<number;i++){
		var input = document.createElement("input");
		input.type = "text";
		input.name = "number" + i;
		container.appendChild(input);			         
		container.appendChild(document.createElement("br"));
	}
} 
);
</script>
<title>SorteioES</title>
</head>
<body>
<div class="center-div">
<form action="BolaoServlet" method="post">
	<div class="row">
		<div class="col-sm">
			<label>Aposta Inicial</label>
			<div class="row">
				<div class="col-sm">
				<label>Aposta Inicial:</label>
				<div id="numeros" name="numeros">
			</div>
		</div>
	</div>
	<button name=btn value="salvar">Salvar</button>
</form>
</div>
					
</body>
</html>