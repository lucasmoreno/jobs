package br.com.testepaggi.business.service.impl;

import android.util.Log;

import br.com.testepaggi.business.api.Api;
import br.com.testepaggi.business.api.vo.request.NovaTransacaoRequestVO;
import br.com.testepaggi.business.api.vo.response.NovaTransacaoResponseVO;
import br.com.testepaggi.business.service.NovaTransacaoService;
import br.com.testepaggi.common.NovaTransacaoFinishedListener;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class NovaTransacaoServiceImpl implements NovaTransacaoService {

    @Override
    public void novaTransacao(final NovaTransacaoFinishedListener listener, NovaTransacaoRequestVO requestVO) {

        Call<NovaTransacaoResponseVO> call = Api.getAdapter().addCharge(requestVO);

        call.enqueue(new Callback<NovaTransacaoResponseVO>() {
            @Override
            public void onResponse(Response<NovaTransacaoResponseVO> response, Retrofit retrofit) {
                if(response.isSuccess()){

                    if(!response.body().status.equalsIgnoreCase("")){
                        listener.onSuccess(true);
                    }else{
                        listener.onSuccess(false);
                    }

                }else{
                    Log.e("Nova Transacao Error","Response Error 4xx/5xx");
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("LOGIN_ERROR","Nova Transacao Request");
                Log.e("LOGIN_ERROR","Nova Transacao Request "+t.getMessage());
                Log.e("LOGIN_ERROR","Nova Transacao Request "+t.getCause());
                Log.e("LOGIN_ERROR","Nova Transacao Request "+t.getStackTrace());
            }

        });

    }
}
