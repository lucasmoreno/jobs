package com.github.rafaelcrz.paggichallenge.api.interfaces;

import com.github.rafaelcrz.paggichallenge.api.models.Transaction;
import com.github.rafaelcrz.paggichallenge.api.models.TransactionCatalog;
import com.github.rafaelcrz.paggichallenge.api.models.PaymentCatalog;
import com.github.rafaelcrz.paggichallenge.api.models.TransactionResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Rafael Felipe on 13/06/2017.
 *
 * Classe de serviços com os endpoints usados
 */

public interface PaggiService {

    String BASE_URL = "https://online.paggi.com/api/v4/"; //base url da api

    //os parametros são do tipo Map, esse Service será chamado em cada Endpoint (cada endpoint tem uma
    //Api com seus respectivos metodos/chamadas. Cabe a cada "api" implementar os metodos de parametros.

    //Alguns enpoints fazem usos de muitos parametros, para fim de organizaçao e manutenção cada api implementa seus metodos
    //no passando como Map para os Call do Service

    @GET("payments")
    Call<PaymentCatalog> getPayments(
            @QueryMap Map<String, String> params
    );

    @GET("charges")
    Call<TransactionCatalog> getCharges(
            @QueryMap Map<String, String> params
    );

    @POST("charges")
    Call<TransactionResult> createCharge(
            @Body Map<String, String> params
    );
}
