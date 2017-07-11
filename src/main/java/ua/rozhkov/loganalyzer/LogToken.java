package ua.rozhkov.loganalyzer;

import java.time.LocalDateTime;

public class LogToken {
	LocalDateTime time;
	HttpMethod method;
	String message;
	
	public LogToken() {
	}
	
	public LogToken(LocalDateTime time, HttpMethod method, String message) {
		this.time = time;
		this.method = method;
		this.message = message;
	}
	
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	public void setMethod(HttpMethod method) {
		this.method = method;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	
	public HttpMethod getMethod() {
		return method;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return "{"+
				this.getTime()+"; "+
				this.getMethod()+";"+
				this.getMessage()+
				"}";
	}
}
