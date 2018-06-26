package com.abelhzo.activemq.rest;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/api/queue/wildfly")
public class QueueProducerController {
	
	@Autowired
	private JmsTemplate jmsTemplateQueue;
	
	@Autowired
	private MessageChannel jmsProducerChannelQueue;
	
	private int i = 1;
	
	@RequestMapping(value = "/sendQueueSimple", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public Object sendQueue(@RequestParam("nombre") String nombre) {
		InfoJmsDTO infoJmsDTO = new InfoJmsDTO();
		infoJmsDTO.setName(nombre);
		infoJmsDTO.setKey(i++);
		infoJmsDTO.setDate(new Date());
		infoJmsDTO.setTypeJms("Queue");
		
//		jmsTemplate.convertAndSend("ExampleAbelHZOQueue", infoJmsDTO);
		jmsTemplateQueue.convertAndSend(infoJmsDTO);
		
		return "{\"mensaje\":\"El dato de almaceno correctamente\"}";
	}
	
	
	/**
	 *TODO: Es importante colocar este receptor dentro de un listener para que cuando se invoque el metodo
	 *no se quede esperando la peticion hasta la llegada de un queue, por que eso ocasiona un bloqueo. 
	 *
	 */
	@RequestMapping(value = "/receiveQueue", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public Object receiveQueue(HttpServletRequest request) {
		System.out.println(request.getRemoteAddr());
		InfoJmsDTO infoJmsDTO = (InfoJmsDTO) jmsTemplateQueue.receiveAndConvert();
		return infoJmsDTO;
	}
	
	
	@RequestMapping(value = "/sendQueueIntegr/{nombre}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public Object sendQueueIntegr(@PathVariable("nombre") String nombre) {
		
		InfoJmsDTO infoJmsDTO = new InfoJmsDTO();
		infoJmsDTO.setName(nombre);
		infoJmsDTO.setKey(i++);
		infoJmsDTO.setDate(new Date());
		infoJmsDTO.setTypeJms("Queue");
		
		jmsProducerChannelQueue.send(MessageBuilder.withPayload(infoJmsDTO).build());
		return "{\"mensaje\":\"El dato de envio correctamente\"}";
	}

}
