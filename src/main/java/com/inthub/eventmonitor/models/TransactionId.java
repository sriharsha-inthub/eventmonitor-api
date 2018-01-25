package com.inthub.eventmonitor.models;

public class TransactionId {
	
	private String global_Transaction_Id;
	private String parent_Transaction_Id;
	private String local_Transaction_Id;
	
	public TransactionId(){
		super();
	}
	
	public TransactionId(	String global_Transaction_Id, 
							String parent_Transaction_Id, 
							String local_Transaction_Id) {
		this.global_Transaction_Id = global_Transaction_Id;
		this.parent_Transaction_Id = parent_Transaction_Id;
		this.local_Transaction_Id = local_Transaction_Id;
	}

	public String getGlobal_Transaction_Id() {
		return global_Transaction_Id;
	}

	public void setGlobal_Transaction_Id(String global_Transaction_Id) {
		this.global_Transaction_Id = global_Transaction_Id;
	}

	public String getParent_Transaction_Id() {
		return parent_Transaction_Id;
	}

	public void setParent_Transaction_Id(String parent_Transaction_Id) {
		this.parent_Transaction_Id = parent_Transaction_Id;
	}

	public String getLocal_Transaction_Id() {
		return local_Transaction_Id;
	}

	public void setLocal_Transaction_Id(String local_Transaction_Id) {
		this.local_Transaction_Id = local_Transaction_Id;
	}

	
}
