package com.kiik.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Card implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private String holder;
	
	@XmlElement
	private String number;
	
	@XmlElement(name="expiration_month")
	private String expirationMonth;
	
	@XmlElement(name="expiration_year")
	private String expirationYear;
	
	@XmlElement
	private String vcc;
	
	public String getHolder() {
		return holder;
	}
	
	public void setHolder(String holder) {
		this.holder = holder;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getExpirationMonth() {
		return expirationMonth;
	}
	
	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	
	public String getExpirationYear() {
		return expirationYear;
	}
	
	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}
	
	public String getVcc() {
		return vcc;
	}
	
	public void setVcc(String vcc) {
		this.vcc = vcc;
	}
}
