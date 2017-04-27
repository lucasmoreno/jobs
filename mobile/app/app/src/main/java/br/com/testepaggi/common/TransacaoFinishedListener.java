package br.com.testepaggi.common;

import br.com.testepaggi.model.Transacoes;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface TransacaoFinishedListener extends BaseCommonListener {

    void onTransacaoSuccess(Transacoes transacoes);

}
