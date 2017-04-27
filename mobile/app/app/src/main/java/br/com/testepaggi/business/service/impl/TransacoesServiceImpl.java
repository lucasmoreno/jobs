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
import br.com.testepaggi.model.ApiResponseType;
import br.com.testepaggi.model.Transacao;
import br.com.testepaggi.model.Transacoes;
import br.com.testepaggi.util.Utils;
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

        if(!Utils.isNetworkAvailable()){
            listener.onError(ApiResponseType.NO_INTENET_CONNECTION);
            return;
        }

        Call<TransacaoResponseVO> call = Api.getAdapter().transacoes(
                requestVO.page,
                requestVO.pageSize,
                (requestVO.start_date == null) ? "":requestVO.start_date,
                (requestVO.end_date == null) ? "":requestVO.end_date
        );

        call.enqueue(new Callback<TransacaoResponseVO>() {
            @Override
            public void onResponse(Response<TransacaoResponseVO> response, Retrofit retrofit) {
                if(response.isSuccess()){

                    Transacoes transacoes = new Transacoes(response.body());

                    if(transacoes.getTrasancoes().size() > 0)
                        listener.onTransacaoSuccess(transacoes);
                    else
                        listener.onError(ApiResponseType.TRANSACAO_EMPTY);

                }else{
                    Log.e("Transacao Error","Response Error 4xx/5xx");
                    listener.onError(ApiResponseType.SERVER_ERROR);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("LOGIN_ERROR","Transacao Request");
                Log.e("LOGIN_ERROR","Transacao Request "+t.getMessage());
                Log.e("LOGIN_ERROR","Transacao Request "+t.getCause());
                Log.e("LOGIN_ERROR","Transacao Request "+t.getStackTrace());
                if(!Utils.isNetworkAvailable()){
                    listener.onError(ApiResponseType.NO_INTENET_CONNECTION);
                }else{
                    listener.onError(ApiResponseType.SERVER_TIMEOUT);
                }
            }
        });

    }

}
