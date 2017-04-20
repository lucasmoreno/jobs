package br.com.testepaggi.business.service.impl;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.testepaggi.business.api.Api;
import br.com.testepaggi.business.api.vo.request.ConsultaBaseRequestVO;
import br.com.testepaggi.business.api.vo.response.PagamentoItemResponseVO;
import br.com.testepaggi.business.api.vo.response.PagamentoResponseVO;
import br.com.testepaggi.business.service.PagamentosService;
import br.com.testepaggi.common.PagamentosFinishedListener;
import br.com.testepaggi.model.Pagamento;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class PagamentoServiceImpl implements PagamentosService {

    @Override
    public void getPagamentos(final PagamentosFinishedListener listener, ConsultaBaseRequestVO requestVO) {

        Call<PagamentoResponseVO> call;

        if(!requestVO.start_date.equalsIgnoreCase("") && !requestVO.end_date.equalsIgnoreCase("")){
            call = Api.getAdapter().pagamentos(
                    requestVO.page,
                    requestVO.pageSize,
                    requestVO.start_date,
                    requestVO.end_date
            );
        }else{
             call = Api.getAdapter().pagamentos(requestVO.page, requestVO.pageSize);
        }

        call.enqueue(new Callback<PagamentoResponseVO>() {
            @Override
            public void onResponse(Response<PagamentoResponseVO> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    List<Pagamento> pagamentos = new ArrayList<Pagamento>();

                    for (PagamentoItemResponseVO responseVO : response.body().result){
                        pagamentos.add(new Pagamento(responseVO));
                    }

                    listener.onSuccess(pagamentos);

                }else{
                    Log.e("Pagamento Error","Response Error 4xx/5xx");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("LOGIN_ERROR","Pagamento Request");
                Log.e("LOGIN_ERROR","Pagamento Request "+t.getMessage());
                Log.e("LOGIN_ERROR","Pagamento Request "+t.getCause());
                Log.e("LOGIN_ERROR","Pagamento Request "+t.getStackTrace());
            }
        });

    }

}
