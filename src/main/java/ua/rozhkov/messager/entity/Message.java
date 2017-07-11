package ua.rozhkov.messager.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Message implements Serializable{
	private int id;
	private LocalDate date;
	private String content;
	
	public Message(){};
	
	public Message(int id, LocalDate date, String content) {
		this.id = id;
		this.date = date;
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "{"+
				"id: "+this.getId()+"; "+
				"date: "+this.getDate()+"; "+
				"content: "+this.getContent()+
				"}";
	}
	
	public int getId() {
		return id;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
