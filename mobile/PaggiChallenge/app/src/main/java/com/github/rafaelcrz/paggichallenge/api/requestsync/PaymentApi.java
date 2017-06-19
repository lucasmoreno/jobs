package com.github.rafaelcrz.paggichallenge.api.requestsync;

import android.content.Context;
import android.util.Log;

import com.github.rafaelcrz.paggichallenge.api.interfaces.DefaultParams;
import com.github.rafaelcrz.paggichallenge.api.interfaces.PaggiListener;
import com.github.rafaelcrz.paggichallenge.api.interfaces.PaggiService;
import com.github.rafaelcrz.paggichallenge.api.models.PaymentCatalog;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rafael Felipe on 13/06/2017.
 * <p>
 * API do Endpoint de Payment
 */

public class PaymentApi implements DefaultParams {

    //Serivce
    private PaggiService paggiService;
    //Map com os parametros da endpoint
    private Map<String, String> params;

    public PaymentApi(Context context) {
        paggiService = PaggiClient.getPaggiClient(context).create(PaggiService.class);
        params = new HashMap<>();
    }

    /**
     * Retorna todos os payments
     * @param paggiListener PaggiListener<TipoModel>
     */
    public void getPayments(final PaggiListener<PaymentCatalog> paggiListener) {

        Call<PaymentCatalog> chargeCatalogCall = paggiService.getPayments(params);
        chargeCatalogCall.enqueue(new Callback<PaymentCatalog>() {
            @Override
            public void onResponse(Call<PaymentCatalog> call, Response<PaymentCatalog> response) {
                paggiListener.onResponde(call, response);
            }

            @Override
            public void onFailure(Call<PaymentCatalog> call, Throwable t) {
                paggiListener.onFailure(call, t);
            }
        });
    }

    //Metodos override da interface DefaultParamns

    @Override
    public void setPage(String page) {
        this.params.put(PAGE, page);
    }

    @Override
    public void setStartDate(String startDate) {
        params.put(START_DATE, startDate);
    }

    @Override
    public void setEndData(String endData) {
        params.put(END_DATE, endData);
    }

    @Override
    public void setPageSize(String pageSize) {
        params.put(PAGE_SIZE, pageSize);
    }
}
