package br.com.testepaggi.ui.presenter;

import br.com.testepaggi.model.Transacao;
import br.com.testepaggi.model.Transacoes;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface TransacaoPresenter extends BasePresenter {

    void refreshTransacaoes();

    void resgataTransacoes();

    void resgataTransacoesComPaginacao();

    void incrementarAdapter(Transacoes transacoes);

    void adicionarMaisItens(Transacao transacao);

}
