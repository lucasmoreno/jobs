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
import br.com.testepaggi.model.ApiResponseType;
import br.com.testepaggi.model.Pagamento;
import br.com.testepaggi.model.Pagamentos;
import br.com.testepaggi.util.Utils;
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

        if(!Utils.isNetworkAvailable()){
            listener.onError(ApiResponseType.NO_INTENET_CONNECTION);
            return;
        }

        Call<PagamentoResponseVO> call = Api.getAdapter().pagamentos(
                requestVO.page,
                requestVO.pageSize,
                (requestVO.start_date == null) ? "":requestVO.start_date,
                (requestVO.end_date == null) ? "":requestVO.end_date
        );

        call.enqueue(new Callback<PagamentoResponseVO>() {
            @Override
            public void onResponse(Response<PagamentoResponseVO> response, Retrofit retrofit) {
                if(response.isSuccess()){

                    Pagamentos pagamentos = new Pagamentos(response.body());

                    if(pagamentos.getPagamentos().size() > 0)
                        listener.onPagamentoSuccess(pagamentos);
                    else
                        listener.onError(ApiResponseType.PAGAMENTO_EMPTY);

                }else{
                    Log.e("Pagamento Error","Response Error 4xx/5xx");
                    listener.onError(ApiResponseType.SERVER_ERROR);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("LOGIN_ERROR","Pagamento Request");
                Log.e("LOGIN_ERROR","Pagamento Request "+t.getMessage());
                Log.e("LOGIN_ERROR","Pagamento Request "+t.getCause());
                Log.e("LOGIN_ERROR","Pagamento Request "+t.getStackTrace());
                if(!Utils.isNetworkAvailable()){
                    listener.onError(ApiResponseType.NO_INTENET_CONNECTION);
                }else{
                    listener.onError(ApiResponseType.SERVER_TIMEOUT);
                }
            }
        });

    }

}
