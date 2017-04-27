package br.com.testepaggi.ui.presenter;

import br.com.testepaggi.model.Pagamento;
import br.com.testepaggi.model.Pagamentos;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface PagamentoPresenter extends BasePresenter {

    void resgataPagamentos();

    void resgataPagamentosComPaginacao();

    void incrementarAdapter(Pagamentos pagamentos);

    void adicionarMaisItens(Pagamento pagamento);

}
