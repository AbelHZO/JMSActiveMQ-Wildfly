/**
 * 
 */
var stompClient;

function connect() {
	var socket = new SockJS('/JMSActiveMQ-Wildfly/myendpoint');
	stompClient = Stomp.over(socket);

	stompClient.connect({}, function(frame) {
		
		console.log('Connected: ' + frame);
		
		stompClient.subscribe('/topic/receive/message', function(response) {
			console.log(JSON.parse(response.body));
			showMessage(JSON.parse(response.body));
		});
		
		stompClient.subscribe('/queue/receive/message', function(response) {
			console.log(JSON.parse(response.body));
			showMessage(JSON.parse(response.body));
		});
	});
	
}

function showMessage(json) {
	var bodyMessage = document.getElementById("bodyMessage");
	bodyMessage.innerHTML += "<tr><td><b>"+json.key+"</b></td><td><i>"+ new Date(json.date) +"</i></td><td><b>" + json.typeJms + "</b></td><td>" + json.name + "</td></tr>";
}

function disconnect() {
	stompClient.disconnect();
	console.log("Disconnected");
}

function send() {	
	console.log("sending");
//	stompClient.send("/app/comenzar", {}, JSON.stringify({"message":"Hola como estas!!!"}));
	stompClient.send("/app/comenzar", {}, "Mensaje enviado desde el front!!!");
}