package com.example.market.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "trades")
public class TradeDocument {
	@Id
	private String id;
	@JsonProperty("s")
	private String symbol;
	@JsonProperty("p")
	private String price;
	@JsonProperty("q")
	private String quantity;
	@JsonProperty("t")
	private long sequenceId;
	@JsonProperty("T")
	private String timestamp;
	@JsonProperty("b")
	private long bid;
	@JsonProperty("a")
	private long ask;

	public TradeDocument() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public long getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(long sequenceId) {
		this.sequenceId = sequenceId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public long getAsk() {
		return ask;
	}

	public void setAsk(long ask) {
		this.ask = ask;
	}

	@Override
	public String toString() {
		return "TradeDocument [id=" + id + ", symbol=" + symbol + ", price=" + price + ", quantity=" + quantity
				+ ", sequenceId=" + sequenceId + ", timestamp=" + timestamp + ", bid=" + bid + ", ask=" + ask + "]";
	}



}
