package br.com.testepaggi.common;

import br.com.testepaggi.model.ApiResponseType;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface BaseCommonListener extends OnApiErrorResponseListener {
    void onError(ApiResponseType error);
}
