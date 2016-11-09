package com.kiik.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Request {

	@XmlElement
	private double amount;
	
	@XmlElement
	private Card card;
	
	@XmlElement
	private List<Intermediary> intermediaries;
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Card getCard() {
		return card;
	}
	
	public void setCard(Card card) {
		this.card = card;
	}
	
	public List<Intermediary> getIntermediaries() {
		return intermediaries;
	}
	
	public void setIntermediaries(List<Intermediary> intermediaries) {
		this.intermediaries = intermediaries;
	}
	
	
}
