package br.com.testepaggi.business.service.impl;

import android.util.Log;

import br.com.testepaggi.business.service.ApiErrorService;
import br.com.testepaggi.common.OnApiErrorResponseListener;
import br.com.testepaggi.model.ApiResponseType;
import retrofit.Response;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class ApiErrorServiceImpl implements ApiErrorService {

    @Override
    public void processErrorResponse(Response response, OnApiErrorResponseListener listener) {

        Log.e("ERROR_RESPONSE",response.message());

        switch (response.code()){
            case 422:
                listener.onApiError(ApiResponseType.ERROR_INSERT_TRANSACAO);
                break;
            default:
                listener.onApiError(ApiResponseType.SERVER_ERROR);
                break;
        }

    }

}
