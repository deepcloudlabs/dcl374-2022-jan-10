package com.example.market.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class WebsocketConfig {
	
	@Bean
	public WebSocketClient websocketClient() {
		var client = new StandardWebSocketClient();
		
		return client;
	}
}
