package com.abelhzo.activemq.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.abelhzo.activemq.dto.InfoJmsDTO;

public class TopicConsumerListener {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
//	@MessageMapping("/topic/message") //Con esta anotacion se invoca el metodo websocket
//	@SendTo("/topic/receive/message") //Con esta anotacion se envia el mensaje a los subscriptores
	public void onMessage(InfoJmsDTO infoJmsDTO) {
		
		System.out.println(infoJmsDTO.getKey() + " - " + 
						   infoJmsDTO.getName() + " - " + 
						   infoJmsDTO.getDate().toString() + " - " + 
						   infoJmsDTO.getTypeJms());
		
		simpMessagingTemplate.convertAndSend("/topic/receive/message", infoJmsDTO);
		
	}

}
