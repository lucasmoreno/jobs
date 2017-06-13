package br.com.paggi.dto;

import java.io.Serializable;
import java.util.List;

public class ChargeRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	private Card card;
	private Integer amount;
	private List< Intermediary > intermediaries;

	public ChargeRequest(){}

	public final Card getCard() {
		return card;
	}

	public final void setCard( Card card ) {
		this.card = card;
	}

	public final Integer getAmount() {
		return amount;
	}

	public final void setAmount( Integer amount ) {
		this.amount = amount;
	}

	public final List< Intermediary > getIntermediaries() {
		return intermediaries;
	}

	public final void setIntermediaries( List< Intermediary > intermediaries ) {
		this.intermediaries = intermediaries;
	}

}
