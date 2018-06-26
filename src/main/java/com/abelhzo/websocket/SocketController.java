package com.abelhzo.websocket;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.abelhzo.activemq.dto.InfoJmsDTO;

@Controller
public class SocketController {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	//Con esta anotacion se invoca el metodo por websocket
	/**
	 * TODO: Es importante invocarlo con el prefijo app (ej. /app/comenzar)
	 */
	@MessageMapping("/comenzar")
	public void messageWebSocket(String message) throws InterruptedException {
		for(int i = 0; i < 10 ; i ++) {
			System.out.println("DENTRO DEL CICLO: " + i);
			InfoJmsDTO infoJmsDTO = new InfoJmsDTO();
			infoJmsDTO.setKey(1000000);
			infoJmsDTO.setTypeJms("Internal WebSocket Tests");
			infoJmsDTO.setName("Test WebSocket: " + message);
			infoJmsDTO.setDate(new Date());
			
			//Para enviar la respuesta al websocket se uso este metodo de SimpMessagingTemplate
			/**
			 * TODO: Es importante colocar el prefijo "topic", o "queue" dependiendo de lo que 
			 * se haya especificado en la configuración. (ej. /topic/receive/message)
			 */
			simpMessagingTemplate.convertAndSend("/topic/receive/message", infoJmsDTO);
			Thread.sleep(1000);
		}
	}

}
