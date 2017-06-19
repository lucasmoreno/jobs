package com.github.rafaelcrz.paggichallenge.api.requestsync;

import android.content.Context;

import com.github.rafaelcrz.paggichallenge.api.interfaces.DefaultParams;
import com.github.rafaelcrz.paggichallenge.api.interfaces.PaggiListener;
import com.github.rafaelcrz.paggichallenge.api.interfaces.PaggiService;
import com.github.rafaelcrz.paggichallenge.api.models.Transaction;
import com.github.rafaelcrz.paggichallenge.api.models.TransactionCatalog;
import com.github.rafaelcrz.paggichallenge.api.models.TransactionResult;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rafael Felipe on 15/06/2017.
 *
 * Api da endpoint de Transacitons
 */

public class TransactionApi implements DefaultParams {

    private PaggiService paggiService;
    private Map<String, String> params;

    //Parametros dessa Endpoint
    private String AMOUNT = "amount";
    private String CARD_ID = "card_id";
    private String CUSTOMER_ID = "customer_id";
    private String INSTALLMENT_NUMBER = "installments_number";

    public TransactionApi(Context context) {
        paggiService = PaggiClient.getPaggiClient(context).create(PaggiService.class);
        params = new HashMap<>();
    }

    /**
     * Realizar uma nova Transacao
     * @param chargePaggiListener Listener<TransactionResult>
     */
    public void newTransaction(final PaggiListener<TransactionResult> chargePaggiListener) {
        Call<TransactionResult> chargeCall = paggiService.createCharge(params);
        chargeCall.enqueue(new Callback<TransactionResult>() {
            @Override
            public void onResponse(Call<TransactionResult> call, Response<TransactionResult> response) {
                chargePaggiListener.onResponde(call, response);
            }

            @Override
            public void onFailure(Call<TransactionResult> call, Throwable t) {
                chargePaggiListener.onFailure(call, t);
            }
        });
    }

    /**
     * Retorna todas as transacoes com paginacao e filtro de data
     * @param chargePaggiListener
     */
    public void getTransaction(final PaggiListener<TransactionCatalog> chargePaggiListener) {
        Call<TransactionCatalog> chargeCatalogCall = paggiService.getCharges(params);
        chargeCatalogCall.enqueue(new Callback<TransactionCatalog>() {
            @Override
            public void onResponse(Call<TransactionCatalog> call, Response<TransactionCatalog> response) {
                chargePaggiListener.onResponde(call, response);
            }

            @Override
            public void onFailure(Call<TransactionCatalog> call, Throwable t) {
                chargePaggiListener.onFailure(call, t);
            }
        });
    }

    //Override de parametros padrao da interface DefaultParamns

    @Override
    public void setPage(String page) {
        params.put(PAGE, page);
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

    //Parametros especificos da enpoint

    public void setAmount(String amount) {
        params.put(AMOUNT, amount);
    }

    public void setInstallmentNumber(String installmentNumber) {
        params.put(INSTALLMENT_NUMBER, installmentNumber);
    }

    public void setCardId(String cardId) {
        params.put(CARD_ID, cardId);
    }

    public void setCustomerId(String customerId) {
        params.put(CUSTOMER_ID, customerId);
    }
}
