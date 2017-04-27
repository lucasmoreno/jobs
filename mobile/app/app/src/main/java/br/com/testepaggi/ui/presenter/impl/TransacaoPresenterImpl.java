package br.com.testepaggi.ui.presenter.impl;

import br.com.testepaggi.business.api.vo.request.ConsultaBaseRequestVO;
import br.com.testepaggi.business.service.TransacoesService;
import br.com.testepaggi.business.service.impl.TransacoesServiceImpl;
import br.com.testepaggi.common.TransacaoFinishedListener;
import br.com.testepaggi.model.ApiResponseType;
import br.com.testepaggi.model.Transacao;
import br.com.testepaggi.model.Transacoes;
import br.com.testepaggi.ui.adapter.TransacaoAdapter;
import br.com.testepaggi.ui.presenter.TransacaoPresenter;
import br.com.testepaggi.ui.view.TransacaoView;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class TransacaoPresenterImpl implements TransacaoPresenter, TransacaoFinishedListener {

    private TransacaoView       view;
    private TransacoesService   service;
    private TransacaoAdapter    adapter;
    private int                 tPaginas;
    private int                 pagina = 1;
    private int                 tamanhoPagina = 10;
    private boolean             temMaisPaginas;

    public TransacaoPresenterImpl(TransacaoView view) {
        this.view = view;
        init();
    }

    @Override
    public void init() {
        this.view.showLoading();
        this.view.carregaAdapter();
        this.service = new TransacoesServiceImpl();
        resgataTransacoes();
    }

    @Override
    public void tentarNovament() {
        if(this.pagina > 1)
            init();
        else
            resgataTransacoesComPaginacao();
    }

    @Override
    public void refreshTransacaoes() {
        this.view.showLoading();
        this.pagina = 1;
        this.temMaisPaginas = false;
        this.resgataTransacoes();
        this.view.hideSwipeRefresh();
    }

    @Override
    public void resgataTransacoes() {
        ConsultaBaseRequestVO requestVO = new ConsultaBaseRequestVO();
        requestVO.page                  = pagina;
        requestVO.pageSize              = tamanhoPagina;
        this.service.getTransacoes(this, requestVO);
    }

    @Override
    public void resgataTransacoesComPaginacao() {
        if(temMaisPaginas){
            view.showLoading();
            ConsultaBaseRequestVO requestVO = new ConsultaBaseRequestVO();
            requestVO.page                  = pagina;
            requestVO.pageSize              = tamanhoPagina;
            this.service.getTransacoes(this, requestVO);
        }
    }

    @Override
    public void incrementarAdapter(Transacoes transacoes) {
        for (Transacao transacao : transacoes.getTrasancoes()) {
            adicionarMaisItens(transacao);
        }
    }

    @Override
    public void adicionarMaisItens(Transacao transacao) {
        adapter.addItem(transacao);
    }

    @Override
    public void onTransacaoSuccess(Transacoes transacoes) {

        if(!this.temMaisPaginas){

            if (transacoes.getTotal() > this.tamanhoPagina){
                this.temMaisPaginas = true;
                this.tPaginas = (Math.round(transacoes.getTotal() / this.tamanhoPagina))+1;
                this.pagina++;
            }

            adapter = new TransacaoAdapter(transacoes, this.view.getContext());
            this.view.mostrarAdapter(adapter);
        }else{
            if(this.pagina == this.tPaginas){
                this.temMaisPaginas = false;
            }else if(this.pagina <= this.tPaginas){
                this.pagina++;
            }

            incrementarAdapter(transacoes);
        }

        view.hideLoading();
    }

    @Override
    public void onError(ApiResponseType error) {
        this.view.hideLoading();
        this.view.showEptyState(error);
    }

    @Override
    public void onApiError(ApiResponseType error) {

    }
}
