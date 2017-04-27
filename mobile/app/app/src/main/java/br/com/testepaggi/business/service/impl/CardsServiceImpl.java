package br.com.testepaggi.business.service.impl;

import android.util.Log;

import br.com.testepaggi.business.api.Api;
import br.com.testepaggi.business.api.vo.request.ConsultaBaseRequestVO;
import br.com.testepaggi.business.api.vo.response.CardsResponseVO;
import br.com.testepaggi.business.service.CardsService;
import br.com.testepaggi.common.CardsFinishedListener;
import br.com.testepaggi.model.ApiResponseType;
import br.com.testepaggi.model.Cards;
import br.com.testepaggi.util.Utils;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class CardsServiceImpl implements CardsService {

    @Override
    public void getCards(final CardsFinishedListener listener, ConsultaBaseRequestVO requestVO) {

        if(!Utils.isNetworkAvailable()){
            listener.onError(ApiResponseType.NO_INTENET_CONNECTION);
            return;
        }

        Call<CardsResponseVO> call = Api.getAdapter().cards(requestVO.page, requestVO.pageSize);

        call.enqueue(new Callback<CardsResponseVO>() {
            @Override
            public void onResponse(Response<CardsResponseVO> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    Cards cards = new Cards(response.body());
                    listener.onCardsSuccess(cards);
                }else{
                    Log.e("Transacao Error","Response Error 4xx/5xx");
                    listener.onError(ApiResponseType.SERVER_ERROR);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("LOGIN_ERROR","Cards Request");
                Log.e("LOGIN_ERROR","Cards Request "+t.getMessage());
                Log.e("LOGIN_ERROR","Cards Request "+t.getCause());
                Log.e("LOGIN_ERROR","Cards Request "+t.getStackTrace());
                if(!Utils.isNetworkAvailable()){
                    listener.onError(ApiResponseType.NO_INTENET_CONNECTION);
                }else{
                    listener.onError(ApiResponseType.SERVER_TIMEOUT);
                }
            }
        });

    }
}
