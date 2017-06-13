package br.com.paggi.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ChargeResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;

	private Integer amount;

	@JsonProperty( "card_id" )
	private String cardId;

	private List< Intermediary > intermediaries = new ArrayList<>();

	public ChargeResponse(){}

	public final String getId() {
		return id;
	}

	public final void setId( String id ) {
		this.id = id;
	}

	public final Integer getAmount() {
		return amount;
	}

	public final void setAmount( Integer amount ) {
		this.amount = amount;
	}

	public final String getCardId() {
		return cardId;
	}

	public final void setCardId( String cardId ) {
		this.cardId = cardId;
	}

	public final List< Intermediary > getIntermediaries() {
		return intermediaries;
	}

	public final void setIntermediaries( List< Intermediary > intermediaries ) {
		this.intermediaries = intermediaries;
	}

}
