package com.kiik.ejb;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import javax.ejb.Stateless;

import com.kiik.ejb.exception.AmountException;
import com.kiik.entity.Intermediary;
import com.kiik.entity.Request;
import com.kiik.entity.Response;

@Stateless
public class Charge {

	/*
	 * Faz o processamento do request
	 */
	public Response process(Request request) throws AmountException{
		Response response = new Response();
		response.setId(generateId(request));
		response.setCardId(generateCardId(request));
		response.setAmount(request.getAmount());
		response.setIntermediaries(request.getIntermediaries());
		calculateIntermediaryAmount(request.getIntermediaries(), request.getAmount());
		return response;
	}
	
	/*
	 * Calcula o amount do intermediário. Caso o amount dos 
	 * intermediários seja maior que o amount total, será gerada uma exception.
	 */
	private void calculateIntermediaryAmount(List<Intermediary> intermediaries, double amount) throws AmountException{
		double amountLimit = 0;
		for(Intermediary intermediary : intermediaries){
			double feeAndFlat = intermediary.getFee() + intermediary.getFlat();
			amountLimit += feeAndFlat;
			if(amountLimit <= amount){
				intermediary.setAmount(feeAndFlat);
			}else {
				throw new AmountException();
			}
			
		}
	}
	
	/*
	 * Gera um id usando número do cartão, data e hora.
	 */
	private String generateId(Request request){
		return Base64.getEncoder()
				.encodeToString((request.getCard().getNumber() +
						LocalDateTime.now()).getBytes());
	}
	
	/*
	 * Gera um card_id usando número do cartão, mês de expiração, data e hora.
	 */
	private String generateCardId(Request request){
		return Base64.getEncoder()
				.encodeToString((request.getCard().getNumber() + 
						request.getCard().getExpirationMonth() + 
						LocalDateTime.now()).getBytes());
	}
}
