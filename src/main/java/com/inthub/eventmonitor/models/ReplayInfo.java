package com.inthub.eventmonitor.models;

public class ReplayInfo {
	
	private int replay_Counter;
	private boolean replay_Flag;
	private String source_Destination;
	private String source_Protocol;
	
	public ReplayInfo(){
		super();
	}
	
	public ReplayInfo(	int Replay_Counter,
						boolean Replay_Flag,
						String Source_Destination,
						String Source_Protocol){
		this.replay_Counter = Replay_Counter;
		this.replay_Flag = Replay_Flag;
		this.source_Destination = Source_Destination;
		this.source_Protocol = Source_Protocol;
		
	}

	public int getReplay_Counter() {
		return replay_Counter;
	}

	public void setReplay_Counter(int replay_Counter) {
		this.replay_Counter = replay_Counter;
	}

	public boolean isReplay_Flag() {
		return replay_Flag;
	}

	public void setReplay_Flag(boolean replay_Flag) {
		this.replay_Flag = replay_Flag;
	}

	public String getSource_Destination() {
		return source_Destination;
	}

	public void setSource_Destination(String source_Destination) {
		this.source_Destination = source_Destination;
	}

	public String getSource_Protocol() {
		return source_Protocol;
	}

	public void setSource_Protocol(String source_Protocol) {
		this.source_Protocol = source_Protocol;
	}
	
	
}
