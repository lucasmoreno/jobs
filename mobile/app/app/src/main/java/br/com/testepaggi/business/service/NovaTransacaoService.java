package br.com.testepaggi.business.service;

import br.com.testepaggi.business.api.vo.request.NovaTransacaoRequestVO;
import br.com.testepaggi.common.NovaTransacaoFinishedListener;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface NovaTransacaoService {
    void novaTransacao(NovaTransacaoFinishedListener listener, NovaTransacaoRequestVO requestVO);
}
