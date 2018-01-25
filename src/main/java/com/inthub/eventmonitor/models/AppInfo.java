package com.inthub.eventmonitor.models;

public class AppInfo {
	
	private String destination_App;
	private String source_App;
	
	public AppInfo(){
		super();
	}
	
	public AppInfo(	String Destination_App, 
					String Source_App){
		this.destination_App = Destination_App;
		this.source_App = Source_App;
	}

	public String getDestination_App() {
		return destination_App;
	}

	public void setDestination_App(String destination_App) {
		this.destination_App = destination_App;
	}

	public String getSource_App() {
		return source_App;
	}

	public void setSource_App(String source_App) {
		this.source_App = source_App;
	}
	
	
}
