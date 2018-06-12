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
	var strQuery = "";

	$.ajax({
			type : "POST",
			url : "./SorteioServlet",
			contentType : "application/json",
			data : strQuery,
			success : function(msg, a, b) {
				console.log( "Recebido : " + msg );
				 $('#tabela').DataTable( {
					 	destroy : true,
				        data: msg,
				        columns: [
				            { data:"id", title: "Sorteio" },
				            { data:"dataAbertura", title: "Data de Abertura" },
				            { data:"dataEncerramento", title: "Data de Encerramento" }
				        ]
				    } );
			} 
	});
	
})

</script>
<title>Insert title here</title>�
</head>
<body>
<div class="container">
			<table id="tabela"></table>
		</div>

</body>
</html>