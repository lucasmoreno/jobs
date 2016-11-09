package com.kiik.rs;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kiik.ejb.Charge;
import com.kiik.ejb.exception.AmountException;
import com.kiik.entity.Intermediary;
import com.kiik.entity.Request;
import com.kiik.entity.Response;

/*
 * End-point /charge
 */

@Stateless
@Path("charge")
public class CreditCardService {

	@EJB
	private Charge charge;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response charge(Request request){
		/*
		 * Em caso de exception, pelo valor a ser pago para os intermediários ser maior que do amount,
		 * retornará uma mensagem indicando o erro

		 */
		try {
			return charge.process(request);
		} catch (AmountException e) {
			Response response = new Response();
			response.setMessage("Valores não compatíveis");
			e.printStackTrace();
			return response;
		}
	}
}
