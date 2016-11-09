package com.kiik.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Intermediary implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private double fee;
	
	@XmlElement
	private double flat;
	
	@XmlElement
	private String description;
	
	@XmlElement
	private double amount;
	
	public double getFee() {
		return fee;
	}
	
	public void setFee(double fee) {
		this.fee = fee;
	}
	
	public double getFlat() {
		return flat;
	}
	
	public void setFlat(double flat) {
		this.flat = flat;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
