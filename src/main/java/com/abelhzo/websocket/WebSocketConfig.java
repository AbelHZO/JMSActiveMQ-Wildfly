package com.abelhzo.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic", "/queue");	  //Prefijo con el que envia mensaje websocket a pagina web.			
		registry.setApplicationDestinationPrefixes("/app");   //Prefijo con el que invoca el metodo websocket
	}

	/**
	 * TODO: Este metodo especificara el endpoint del websocket, es importante colocarlo en el 
	 * javascript con el contexto.
	 */
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/myendpoint").withSockJS();
	}

}
