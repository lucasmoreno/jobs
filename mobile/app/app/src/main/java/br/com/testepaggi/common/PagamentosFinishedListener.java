package br.com.testepaggi.common;

import java.util.List;

import br.com.testepaggi.model.Pagamento;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface PagamentosFinishedListener extends BaseCommonListener {

    void onSuccess(List<Pagamento> pagamentos);

}
