package com.example.market.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.market.service.MarketWebsocketService;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {
	private MarketWebsocketService marketWebsocketService;
	
	public WebsocketConfig(MarketWebsocketService marketWebsocketService) {
		this.marketWebsocketService = marketWebsocketService;
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(marketWebsocketService, "/trades");
	}

	@Bean
	public WebSocketClient websocketClient() {
		var client = new StandardWebSocketClient();
		
		return client;
	}

}
