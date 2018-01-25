package com.inthub.eventmonitor.models;

public class MessageInfo {
	
	private String msg_Format;
	private String service_Type;
	
	public MessageInfo(){
		super();
	}
	
	public MessageInfo( String Msg_Format,
						String Service_Type){
		this.msg_Format = Msg_Format;
		this.service_Type = Service_Type;
	}

	public String getMsg_Format() {
		return msg_Format;
	}

	public void setMsg_Format(String msg_Format) {
		this.msg_Format = msg_Format;
	}

	public String getService_Type() {
		return service_Type;
	}

	public void setService_Type(String service_Type) {
		this.service_Type = service_Type;
	}
	
	
	
}
