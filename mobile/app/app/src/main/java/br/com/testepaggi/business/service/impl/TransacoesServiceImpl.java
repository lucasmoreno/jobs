package br.com.testepaggi.business.service.impl;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.testepaggi.business.api.Api;
import br.com.testepaggi.business.api.vo.request.ConsultaBaseRequestVO;
import br.com.testepaggi.business.api.vo.response.TransacaoItemResponseVO;
import br.com.testepaggi.business.api.vo.response.TransacaoResponseVO;
import br.com.testepaggi.business.service.TransacoesService;
import br.com.testepaggi.common.TransacaoFinishedListener;
import br.com.testepaggi.model.Transacao;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class TransacoesServiceImpl implements TransacoesService {

    @Override
    public void getTransacoes(final TransacaoFinishedListener listener, ConsultaBaseRequestVO requestVO) {

        Call<TransacaoResponseVO> call;

        if(!requestVO.start_date.equalsIgnoreCase("") && !requestVO.end_date.equalsIgnoreCase("")){
            call = Api.getAdapter().transacoes(
                    requestVO.page,
                    requestVO.pageSize,
                    requestVO.start_date,
                    requestVO.end_date
            );
        }else{
             call = Api.getAdapter().transacoes(requestVO.page, requestVO.pageSize);
        }

        call.enqueue(new Callback<TransacaoResponseVO>() {
            @Override
            public void onResponse(Response<TransacaoResponseVO> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    List<Transacao> transacoes = new ArrayList<Transacao>();

                    for (TransacaoItemResponseVO responseVO : response.body().result){
                        transacoes.add(new Transacao(responseVO));
                    }

                    listener.onSuccess(transacoes);

                }else{
                    Log.e("Transacao Error","Response Error 4xx/5xx");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("LOGIN_ERROR","Transacao Request");
                Log.e("LOGIN_ERROR","Transacao Request "+t.getMessage());
                Log.e("LOGIN_ERROR","Transacao Request "+t.getCause());
                Log.e("LOGIN_ERROR","Transacao Request "+t.getStackTrace());
            }
        });

    }

}
