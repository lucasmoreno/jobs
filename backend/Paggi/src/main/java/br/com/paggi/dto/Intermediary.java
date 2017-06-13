package br.com.paggi.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude( Include.NON_NULL )
public final class Intermediary implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer fee;

	private Integer flat;

	private String description;

	private Integer amount;

	public Intermediary(){}

	public final Integer getFee() {
		return fee;
	}

	public final void setFee( Integer fee ) {
		this.fee = fee;
	}

	public final Integer getFlat() {
		return flat;
	}

	public final void setFlat( Integer flat ) {
		this.flat = flat;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription( String description ) {
		this.description = description;
	}

	public final Integer getAmount() {
		return amount;
	}

	public final void setAmount( Integer amount ) {
		this.amount = amount;
	}

}
