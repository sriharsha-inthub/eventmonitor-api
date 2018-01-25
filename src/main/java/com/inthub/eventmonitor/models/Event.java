package com.inthub.eventmonitor.models;

public class Event {
	
	private String name;
	private String timestamp;
	
	
	
	public Event(	String name,
					String timestamp){
		this.name = name;
		this.timestamp = timestamp;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
