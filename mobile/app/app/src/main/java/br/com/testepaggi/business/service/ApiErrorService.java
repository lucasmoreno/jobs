package br.com.testepaggi.business.service;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

import br.com.testepaggi.common.OnApiErrorResponseListener;
import retrofit.Response;

public interface ApiErrorService {

    void processErrorResponse(Response response, OnApiErrorResponseListener listener);

}
