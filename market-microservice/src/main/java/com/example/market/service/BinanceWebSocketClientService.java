package com.example.market.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import com.example.market.document.TradeDocument;
import com.example.market.repository.MarketMongoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BinanceWebSocketClientService implements WebSocketHandler {
	@Value("${binanceWsUrl}")
	private String binanceWsUrl;

	private WebSocketClient webSocketClient;	
	private MarketMongoRepository marketMongoRepository;
	private ObjectMapper objectMapper;
	private ApplicationEventPublisher eventPublisher;
	
	public BinanceWebSocketClientService(WebSocketClient webSocketClient, MarketMongoRepository marketMongoRepository,
			ObjectMapper objectMapper,
			ApplicationEventPublisher eventPublisher) {
		this.webSocketClient = webSocketClient;
		this.marketMongoRepository = marketMongoRepository;
		this.objectMapper = objectMapper;
		this.eventPublisher = eventPublisher;
	}

	@PostConstruct
	public void connectToBinance() {
		webSocketClient.doHandshake(this, binanceWsUrl);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the Binance server.");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		var tradeMessage = (String) message.getPayload();
		var tradeDocument = objectMapper.readValue(tradeMessage, TradeDocument.class);
		marketMongoRepository.save(tradeDocument);
		eventPublisher.publishEvent(tradeDocument);
		//System.err.println(tradeDocument);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable t) throws Exception {
		System.err.println("Error in websocket: " + t.getMessage());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("Websocket connection is closed.");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
