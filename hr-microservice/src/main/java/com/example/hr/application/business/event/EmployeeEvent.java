package com.example.hr.application.business.event;

public class EmployeeEvent {

	private final String eventId;

	public EmployeeEvent(String eventId) {
		this.eventId = eventId;
	}

	public String getEventId() {
		return eventId;
	}

}
