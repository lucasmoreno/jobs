package br.com.paggi.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Card implements Serializable{

	private static final long serialVersionUID = 1L;

	private String holder;

	private String number;

	@JsonProperty( "expiration_month" )
	private String expirationMonth;

	@JsonProperty( "expiration_year" )
	private String expirationYear;

	private String cvv;

	public Card(){}

	public final String getNumber() {
		return number;
	}

	public final void setNumber( String number ) {
		this.number = number;
	}

	public final String getHolder() {
		return holder;
	}

	public final void setHolder( String holder ) {
		this.holder = holder;
	}

	public final String getExpirationMonth() {
		return expirationMonth;
	}

	public final void setExpirationMonth( String expirationMonth ) {
		this.expirationMonth = expirationMonth;
	}

	public final String getExpirationYear() {
		return expirationYear;
	}

	public final void setExpirationYear( String expirationYear ) {
		this.expirationYear = expirationYear;
	}

	public final String getCvv() {
		return cvv;
	}

	public final void setCvv( String cvv ) {
		this.cvv = cvv;
	}

}
