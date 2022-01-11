package com.example.crm.dto;

public class ErrorResponse {
	private final String source;
	private final String message;

	public ErrorResponse(String source, String message) {
		this.source = source;
		this.message = message;
	}

	public String getSource() {
		return source;
	}

	public String getMessage() {
		return message;
	}

}
