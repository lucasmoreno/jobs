package com.kiik.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {
	
	@XmlElement
	private String id;
	
	@XmlElement
	private Double amount;
	
	@XmlElement(name="card_id")
	private String cardId;
	
	@XmlElement
	private List<Intermediary> intermediaries;
	
	@XmlElement
	private String message;

	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getCardId() {
		return cardId;
	}
	
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	public List<Intermediary> getIntermediaries() {
		return intermediaries;
	}
	
	public void setIntermediaries(List<Intermediary> intermediaries) {
		this.intermediaries = intermediaries;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
