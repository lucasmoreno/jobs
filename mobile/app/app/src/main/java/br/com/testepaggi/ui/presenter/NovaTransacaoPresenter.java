package br.com.testepaggi.ui.presenter;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface NovaTransacaoPresenter extends BasePresenter {

    boolean validaValor(long valor);

    void getCards();

    void enviaNovaTransacao(String valor, int parcelas);

}
