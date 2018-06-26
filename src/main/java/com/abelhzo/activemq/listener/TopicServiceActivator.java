package com.abelhzo.activemq.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.abelhzo.activemq.dto.InfoJmsDTO;

public class TopicServiceActivator {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

//	@ServiceActivator(inputChannel = "jmsConsumerChannelTopic")
	public void onMessage(InfoJmsDTO infoJmsDTO) {
		
		System.out.println(infoJmsDTO.getKey() + " - " + 
				   		   infoJmsDTO.getName() + " - " + 
				   		   infoJmsDTO.getDate().toString() + " - " + 
				   		   infoJmsDTO.getTypeJms());
		
		simpMessagingTemplate.convertAndSend("/topic/receive/message", infoJmsDTO);
		
	}
	
}
