package com.inthub.eventmonitor.models;

public class Errors {
	
	private String code;
	private String severity;
	private String text;
	
	
	public Errors(){
		super();
	}
	
	public Errors(	String Code,
					String Severity,
					String Text){
		this.code = Code;
		this.severity = Severity;
		this.text = Text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	

}
