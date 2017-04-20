package br.com.testepaggi.common;

import java.util.List;

import br.com.testepaggi.model.Transacao;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface TransacaoFinishedListener extends BaseCommonListener {

    void onSuccess(List<Transacao> transacaoList);

}
