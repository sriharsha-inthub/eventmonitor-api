package com.inthub.eventmonitor.models;

public class BusinessIdentifiers {
	
	private String business_Identifier1;
	private String business_Identifier2;
	private String business_Identifier3;
	private String business_Identifier4;
	
	public BusinessIdentifiers(){
		super();
	}
	
	public BusinessIdentifiers(	String Business_Identifier1, 
								String Business_Identifier2, 
								String Business_Identifier3, 
								String Business_Identifier4){
		this.business_Identifier1 = Business_Identifier1;
		this.business_Identifier2 = Business_Identifier2;
		this.business_Identifier3 = Business_Identifier3;
		this.business_Identifier4 = Business_Identifier4;
	}
	
	public String getBusiness_Identifier1() {
		return business_Identifier1;
	}

	public void setBusiness_Identifier1(String business_Identifier1) {
		this.business_Identifier1 = business_Identifier1;
	}

	public String getBusiness_Identifier2() {
		return business_Identifier2;
	}

	public void setBusiness_Identifier2(String business_Identifier2) {
		this.business_Identifier2 = business_Identifier2;
	}
	
	public String getBusiness_Identifier3() {
		return business_Identifier3;
	}

	public void setBusiness_Identifier3(String business_Identifier3) {
		this.business_Identifier3 = business_Identifier3;
	}
	
	public String getBusiness_Identifier4() {
		return business_Identifier4;
	}

	public void setBusiness_Identifier4(String business_Identifier4) {
		this.business_Identifier4 = business_Identifier4;
	}
	

	
}
