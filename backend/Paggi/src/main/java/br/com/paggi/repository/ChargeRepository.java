package br.com.paggi.repository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.paggi.dto.ChargeRequest;
import br.com.paggi.dto.ChargeResponse;
import br.com.paggi.facade.ChargeFacade;

@RestController
@RequestMapping( "/charge" )
public class ChargeRepository{

	@RequestMapping( method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity< ChargeResponse > post( @RequestBody ChargeRequest requestDTO ) {
		ChargeResponse response = new ChargeFacade().calculate( requestDTO );
		return new ResponseEntity< ChargeResponse >( response, HttpStatus.OK );
	}

}
