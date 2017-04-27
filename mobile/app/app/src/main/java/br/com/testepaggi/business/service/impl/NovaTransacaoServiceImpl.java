package br.com.testepaggi.business.service.impl;

import android.util.Log;

import br.com.testepaggi.business.api.Api;
import br.com.testepaggi.business.api.vo.request.NovaTransacaoRequestVO;
import br.com.testepaggi.business.api.vo.response.NovaTransacaoResponseVO;
import br.com.testepaggi.business.service.ApiErrorService;
import br.com.testepaggi.business.service.NovaTransacaoService;
import br.com.testepaggi.common.NovaTransacaoFinishedListener;
import br.com.testepaggi.model.ApiResponseType;
import br.com.testepaggi.util.Utils;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class NovaTransacaoServiceImpl implements NovaTransacaoService {

    ApiErrorService apiErrorService = new ApiErrorServiceImpl();

    @Override
    public void novaTransacao(final NovaTransacaoFinishedListener listener, NovaTransacaoRequestVO requestVO) {

        if(!Utils.isNetworkAvailable()){
            listener.onError(ApiResponseType.NO_INTENET_CONNECTION);
            return;
        }

        Call<NovaTransacaoResponseVO> call = Api.getAdapter().addCharge(requestVO);

        call.enqueue(new Callback<NovaTransacaoResponseVO>() {
            @Override
            public void onResponse(Response<NovaTransacaoResponseVO> response, Retrofit retrofit) {

                if(response.isSuccess()){
                    listener.onSuccess(true);
                }else{
                    Log.e("Nova Transacao Error","Response Error 4xx/5xx");
                    if(response.code() != 402)
                        apiErrorService.processErrorResponse(response, listener);
                    else
                        listener.onSuccess(true);
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("LOGIN_ERROR","Nova Transacao Request");
                Log.e("LOGIN_ERROR","Nova Transacao Request "+t.getMessage());
                Log.e("LOGIN_ERROR","Nova Transacao Request "+t.getCause());
                Log.e("LOGIN_ERROR","Nova Transacao Request "+t.getStackTrace());
                if(!Utils.isNetworkAvailable()){
                    listener.onError(ApiResponseType.NO_INTENET_CONNECTION);
                }else{
                    listener.onError(ApiResponseType.SERVER_TIMEOUT);
                }
            }

        });

    }
}
