package com.inthub.eventmonitor.models;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jsondoc.core.annotation.ApiObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection="eventlogs")
@ApiObject
public class MonitoringEvent implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1855688592331070432L;

	@Id
    private String id;
	
	private Event event;
	private String business_Process;
	private TransactionId transactionId;
	private AppInfo appInfo;
	private MessageInfo messageInfo;
	private TransactionHierarchy transactionHierarchy;
	private BusinessIdentifiers businessIdentifiers;
	private boolean is_Error;
	private Errors errors;
	private ReplayInfo replayInfo;
	private byte[] payload;
	
	public MonitoringEvent(){
		super();
	}
	
	public MonitoringEvent(	Event event,
									String Business_Process,
									TransactionId TransactionId,
									AppInfo AppInfo,
									MessageInfo MessageInfo,
									TransactionHierarchy TransactionHierarchy,
									BusinessIdentifiers BusinessIdentifiers,
									boolean Is_Error,
									Errors Errors,
									ReplayInfo ReplayInfo,
									byte[] Payload){
		this.event = event;
		this.business_Process = Business_Process;
		this.transactionId = TransactionId;
		this.appInfo = AppInfo;
		this.messageInfo = MessageInfo;
		this.transactionHierarchy = TransactionHierarchy;
		this.businessIdentifiers = BusinessIdentifiers;
		this.is_Error = Is_Error;
		this.errors = Errors;
		this.replayInfo = ReplayInfo;
		this.payload = Payload;
	}
	

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getBusiness_Process() {
		return business_Process;
	}

	public void setBusiness_Process(String business_Process) {
		this.business_Process = business_Process;
	}

	public TransactionId getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(TransactionId transactionId) {
		this.transactionId = transactionId;
	}

	public AppInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(AppInfo appInfo) {
		this.appInfo = appInfo;
	}

	public MessageInfo getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(MessageInfo messageInfo) {
		this.messageInfo = messageInfo;
	}

	public TransactionHierarchy getTransactionHierarchy() {
		return transactionHierarchy;
	}

	public void setTransactionHierarchy(TransactionHierarchy transactionHierarchy) {
		this.transactionHierarchy = transactionHierarchy;
	}

	public BusinessIdentifiers getBusinessIdentifiers() {
		return businessIdentifiers;
	}

	public void setBusinessIdentifiers(BusinessIdentifiers businessIdentifiers) {
		this.businessIdentifiers = businessIdentifiers;
	}

	public boolean isIs_Error() {
		return is_Error;
	}

	public void setIs_Error(boolean is_Error) {
		this.is_Error = is_Error;
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}

	public ReplayInfo getReplayInfo() {
		return replayInfo;
	}

	public void setReplayInfo(ReplayInfo replayInfo) {
		this.replayInfo = replayInfo;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	@Override
    public String toString() {
    	ObjectMapper mapper = new ObjectMapper();
    	
    	String jsonString = "";
		try {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			jsonString = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
    	return jsonString;
    }
	
}
