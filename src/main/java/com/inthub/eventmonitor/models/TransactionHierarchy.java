package com.inthub.eventmonitor.models;

public class TransactionHierarchy {
	
	private String applicationName;
	private String broker_Name;
	private String executionGrp;
	private String msgFlowName;
	
	public TransactionHierarchy(){
		super();
	}
	
	public TransactionHierarchy(	String ApplicationName,
									String Broker_Name,
									String ExecutionGrp,
									String MsgFlowName){
		this.applicationName = ApplicationName;
		this.broker_Name = Broker_Name;
		this.executionGrp = ExecutionGrp;
		this.msgFlowName = MsgFlowName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getBroker_Name() {
		return broker_Name;
	}

	public void setBroker_Name(String broker_Name) {
		this.broker_Name = broker_Name;
	}

	public String getExecutionGrp() {
		return executionGrp;
	}

	public void setExecutionGrp(String executionGrp) {
		this.executionGrp = executionGrp;
	}

	public String getMsgFlowName() {
		return msgFlowName;
	}

	public void setMsgFlowName(String msgFlowName) {
		this.msgFlowName = msgFlowName;
	}
	
	

}
