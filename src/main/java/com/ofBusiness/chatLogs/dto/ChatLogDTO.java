package com.ofBusiness.chatLogs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatLogDTO {
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public boolean isSent() {
		return isSent;
	}
	public void setSent(boolean isSent) {
		this.isSent = isSent;
	}
	
	
	@NotBlank(message = "message is mandatory")
	String message;
	
	@NotNull(message = "timestamp is mandatory")
	Long timestamp;
	
	@JsonProperty(value = "isSent")
	boolean isSent;

}
