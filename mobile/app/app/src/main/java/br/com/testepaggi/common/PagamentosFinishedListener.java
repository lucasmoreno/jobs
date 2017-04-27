package br.com.testepaggi.common;

import br.com.testepaggi.model.Pagamentos;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface PagamentosFinishedListener extends BaseCommonListener {

    void onPagamentoSuccess(Pagamentos pagamentos);

}
