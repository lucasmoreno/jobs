package br.com.testepaggi.business.service;

import br.com.testepaggi.business.api.vo.request.ConsultaBaseRequestVO;
import br.com.testepaggi.common.PagamentosFinishedListener;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface PagamentosService {
    void getPagamentos(PagamentosFinishedListener listener, ConsultaBaseRequestVO requestVO);
}
