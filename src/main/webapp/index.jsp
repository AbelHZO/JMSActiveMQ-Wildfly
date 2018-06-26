<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/sockjs-0.3.4.js"></script>
<script type="text/javascript" src="js/stomp.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<title>Mensajes Recibidos</title>
</head>
<body>
<button onclick="connect()">Conectar</button> 
<button onclick="disconnect()">Desconectar</button> <br><br>
<button onclick="send()">Comenzar</button><br><br>
<table border="1">
	<thead>
		<tr>
			<th>ID</th>
			<th>HORA</th>
			<th>TIPO</th>
			<th>MENSAJE</th>
		</tr>
	</thead>
	<tbody id="bodyMessage">
	</tbody>
</table>

</body>
</html>