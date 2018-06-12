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
				            { data:"nome", title: "Organizador" },
				            { data:"part", title: "Participantes" },
				            { data:"dataAbertura", title: "Data de Abertura" },
				            { data:"dataEncerramento", title: "Data de Encerramento" },
				            { data:"numIncial", title: "Números Apostados" }
				        ]
				    } );
				 
				 var number = msg.numPorAposta;
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
	});
	
	
	
    function addFields(){
        // Number of inputs to create
        var number = msg.numAposta;
        // Container <div> where dynamic content will be placed
        var container = document.getElementById("numeros");
        // Clear previous contents of the container
        while (container.hasChildNodes()) {
            container.removeChild(container.lastChild);
        }
        for (i=0;i<number;i++){
            // Append a node with a random text
            //container.appendChild(document.createTextNode("Member " + (i+1)));
            // Create an <input> element, set its type and name attributes
            var input = document.createElement("input");
            input.type = "text";
            input.name = "number" + i;
            container.appendChild(input);
            // Append a line break 
            container.appendChild(document.createElement("br"));
        }
    }

	
})

</script>
<title>SorteioES</title>
</head>
<body>
<div class="row">
		<div class="col-sm">
			<label>Números</label>
			<div id="numeros" name="numeros">
			</div>
		</div>
</div>
<div class="container">
			<table id="tabela"></table>
		</div>

</body>
</html>