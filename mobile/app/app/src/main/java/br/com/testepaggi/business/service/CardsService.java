package br.com.testepaggi.business.service;

import br.com.testepaggi.business.api.vo.request.ConsultaBaseRequestVO;
import br.com.testepaggi.common.CardsFinishedListener;
import br.com.testepaggi.common.TransacaoFinishedListener;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface CardsService {
    void getCards(CardsFinishedListener listener, ConsultaBaseRequestVO requestVO);
}
