package br.com.testepaggi.ui.presenter.impl;

import br.com.testepaggi.business.api.vo.request.ConsultaBaseRequestVO;
import br.com.testepaggi.business.service.PagamentosService;
import br.com.testepaggi.business.service.impl.PagamentoServiceImpl;
import br.com.testepaggi.common.PagamentosFinishedListener;
import br.com.testepaggi.model.ApiResponseType;
import br.com.testepaggi.model.Pagamento;
import br.com.testepaggi.model.Pagamentos;
import br.com.testepaggi.ui.adapter.PagamentosAdapter;
import br.com.testepaggi.ui.presenter.PagamentoPresenter;
import br.com.testepaggi.ui.view.PagamentoView;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class PagamentoPresenterImpl implements PagamentoPresenter, PagamentosFinishedListener {

    private PagamentoView       view;
    private PagamentosService   service;
    private PagamentosAdapter   adapter;
    private int                 tPaginas;
    private int                 pagina = 1;
    private int                 tamanhoPagina = 10;
    private boolean             temMaisPaginas;

    public PagamentoPresenterImpl(PagamentoView view) {
        this.view = view;
        init();
    }

    @Override
    public void init() {
        this.view.showLoading();
        this.view.carregaAdapter();
        this.service = new PagamentoServiceImpl();
        resgataPagamentos();
    }

    @Override
    public void tentarNovament() {
        if(pagina > 1)
            init();
        else
            resgataPagamentosComPaginacao();
    }

    @Override
    public void resgataPagamentos() {
        ConsultaBaseRequestVO requestVO = new ConsultaBaseRequestVO();
        requestVO.page                  = pagina;
        requestVO.pageSize              = tamanhoPagina;
        this.service.getPagamentos(this, requestVO);
    }

    @Override
    public void resgataPagamentosComPaginacao() {
        if(temMaisPaginas){
            view.showLoading();
            ConsultaBaseRequestVO requestVO = new ConsultaBaseRequestVO();
            requestVO.page                  = pagina;
            requestVO.pageSize              = tamanhoPagina;
            this.service.getPagamentos(this, requestVO);
        }
    }

    @Override
    public void incrementarAdapter(Pagamentos pagamentos) {
        for (Pagamento pagamento : pagamentos.getPagamentos()) {
            adicionarMaisItens(pagamento);
        }
    }

    @Override
    public void adicionarMaisItens(Pagamento pagamento) {
        adapter.addItem(pagamento);
    }

    @Override
    public void onPagamentoSuccess(Pagamentos pagamentos) {

        if(!this.temMaisPaginas){

            if (pagamentos.getTotal() > this.tamanhoPagina){
                this.temMaisPaginas = true;
                this.tPaginas = (Math.round(pagamentos.getTotal() / this.tamanhoPagina))+1;
                this.pagina++;
            }

            adapter = new PagamentosAdapter(pagamentos, this.view.getContext());
            this.view.mostrarAdapter(adapter);
        }else{
            if(this.pagina == this.tPaginas){
                this.temMaisPaginas = false;
            }else if(this.pagina <= this.tPaginas){
                this.pagina++;
            }

            incrementarAdapter(pagamentos);
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
