package com.abelhzo.activemq.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abelhzo.activemq.dto.InfoJmsDTO;

@RestController
@RequestMapping("/api/topic/wildfly")
public class TopicProducerController {
	
	@Autowired
	private JmsTemplate jmsTemplateTopic;
	
	@Autowired
	private MessageChannel jmsProducerChannelTopic;
	
	private int i = 1;
	
	@RequestMapping(value = "/sendTopicSimple", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public Object sendTopicSimple(@RequestParam("nombre") String nombre) {
		
		InfoJmsDTO infoJmsDTO = new InfoJmsDTO();
		infoJmsDTO.setName(nombre);
		infoJmsDTO.setKey(i++);
		infoJmsDTO.setDate(new Date());
		infoJmsDTO.setTypeJms("Topic");
		
//		jmsTemplate.convertAndSend("ExampleAbelHZOQueue", infoJmsDTO);
		jmsTemplateTopic.convertAndSend(infoJmsDTO);
		return "{\"mensaje\":\"El dato de envio correctamente\"}";
	}
	
	@RequestMapping(value = "/sendTopicIntegr/{nombre}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public Object sendTopicIntegr(@PathVariable("nombre") String nombre) {
		
		InfoJmsDTO infoJmsDTO = new InfoJmsDTO();
		infoJmsDTO.setName(nombre);
		infoJmsDTO.setKey(i++);
		infoJmsDTO.setDate(new Date());
		infoJmsDTO.setTypeJms("Topic");
		
		jmsProducerChannelTopic.send(MessageBuilder.withPayload(infoJmsDTO).build());
		return "{\"mensaje\":\"El dato de envio correctamente\"}";
	}	

}
