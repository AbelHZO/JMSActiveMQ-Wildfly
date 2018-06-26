package com.abelhzo.activemq.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.abelhzo.activemq.dto.InfoJmsDTO;

public class QueueConsumerListener {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	public void onMessage(InfoJmsDTO infoJmsDTO) {
		
		System.out.println(infoJmsDTO.getKey() + " - " + 
						   infoJmsDTO.getName() + " - " + 
						   infoJmsDTO.getDate().toString() + " - " + 
						   infoJmsDTO.getTypeJms());
		
		simpMessagingTemplate.convertAndSend("/queue/receive/message", infoJmsDTO);
		
	}

}
