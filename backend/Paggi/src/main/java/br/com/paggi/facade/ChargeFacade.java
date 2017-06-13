package br.com.paggi.facade;

import java.util.UUID;

import br.com.paggi.dto.ChargeRequest;
import br.com.paggi.dto.ChargeResponse;

public final class ChargeFacade{

	public ChargeResponse calculate( ChargeRequest chargeRequestDTO ) {
		ChargeResponse response = new ChargeResponse();
		response.setId( UUID.randomUUID().toString() );
		response.setCardId( UUID.randomUUID().toString() );
		response.setAmount( chargeRequestDTO.getAmount() );

		chargeRequestDTO.getIntermediaries().forEach( intermediaryDTO -> {
			intermediaryDTO.setAmount( intermediaryDTO.getFee() + intermediaryDTO.getFlat() );
			response.getIntermediaries().add( intermediaryDTO );
		} );

		return response;
	}

}
