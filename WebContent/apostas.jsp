<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./resources/css/bootstrap.min.css"></link>
<link rel="stylesheet" type="text/css" href="./resources/datatables.min.css"></link>
<script src="./resources/js/jquery-3.3.1.js"></script>		
<script src="./resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./resources/datatables.min.js"></script>
<script type="text/javascript" src="./resources/Buttons-1.5.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="./resources/Buttons-1.5.1/js/buttons.colVis.min.js"></script>
<script>
$(document).ready(function ()  {
	var params = (new URL(document.location)).searchParams;
	var id = params.get("sorteioId");

	$.ajax({
			type : "POST",
			url : "./ApostasServlet",
			contentType : "application/json",
			data : id,
			success : function(msg, a, b) {
				console.log( "Recebido : " + msg );
				 $('#tabela').DataTable( {
					 	destroy : true,
				        data: msg,
				        columns: [
				            { data:"organizador", title: "Organizador" },
				            { data:"numPart", title: "Participantes" },
				            { data:"dataAbertura", title: "Data de Abertura" },
				            { data:"dataEnc", title: "Data de Encerramento" },
				            { data:"numApostados", title: "Números Apostados" }
				        ]
				    } );
				 
				 var number = msg[0].numPorAposta;
			     var container = document.getElementById("numeros");			        
			     while (container.hasChildNodes()) {
			     	container.removeChild(container.lastChild);
			     }
			     for (i=0;i<number;i++){
			     	var input = document.createElement("input");
			     	input.type = "text";
			     	input.name = "number" + i;
			     	input.style = "width: 50px; margin-left: 10px;"
			     	container.appendChild(input);			         
			     	//container.appendChild(document.createElement("br"));
			        }
			} 
	});
	
	
})

</script>
<title>SorteioES</title>
</head>
<body>
<h1>Aposta</h1>

<div>
	<label>Números: </label>
	<div id="numeros" name="numeros" style = "display: inline-block;">
	</div>
	<button style="margin-left: 40px;">Apostar</button>
</div>

<h1 style="margin-top: 30px;" >Bolão</h1>

<div style="margin-top: 30px;">
	<table id="tabela"></table>
</div>

<button>Criar Bolão</button>

</body>
</html>