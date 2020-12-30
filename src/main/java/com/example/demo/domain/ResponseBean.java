package com.example.demo.domain;

public class ResponseBean {

	private String status;
	private String reason;
	private Object data;
	
	public ResponseBean() {
		
	}
	public ResponseBean(String status,String reason) {
		this.status=status;
		this.reason=reason;
	}
	public ResponseBean(String status,String reason,Object data) {
		this.status=status;
		this.reason=reason;
		this.data=data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
