package br.com.testepaggi.ui.presenter.impl;

import br.com.testepaggi.R;
import br.com.testepaggi.business.api.vo.request.ConsultaBaseRequestVO;
import br.com.testepaggi.business.api.vo.request.NovaTransacaoRequestVO;
import br.com.testepaggi.business.service.CardsService;
import br.com.testepaggi.business.service.NovaTransacaoService;
import br.com.testepaggi.business.service.impl.CardsServiceImpl;
import br.com.testepaggi.business.service.impl.NovaTransacaoServiceImpl;
import br.com.testepaggi.common.CardsFinishedListener;
import br.com.testepaggi.common.NovaTransacaoFinishedListener;
import br.com.testepaggi.model.ApiResponseType;
import br.com.testepaggi.model.Card;
import br.com.testepaggi.model.Cards;
import br.com.testepaggi.ui.presenter.NovaTransacaoPresenter;
import br.com.testepaggi.ui.view.NovaTransacaoView;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class NovaTransacaoPresenterImpl implements NovaTransacaoPresenter, NovaTransacaoFinishedListener, CardsFinishedListener {

    private NovaTransacaoView       view;
    private NovaTransacaoService    service;
    private CardsService            cardsService;
    private Cards                   cards;
    private String                  valor;
    private int                     parcelas;

    public NovaTransacaoPresenterImpl(NovaTransacaoView view) {
        this.view = view;
        init();
    }

    @Override
    public void init() {
        this.service        = new NovaTransacaoServiceImpl();
        this.cardsService   = new CardsServiceImpl();
        this.getCards();
    }

    @Override
    public void tentarNovament() {
        enviaNovaTransacao(valor, parcelas);
    }

    @Override
    public boolean validaValor(long valor) {
        if(valor > 0)
            return true;
        else
            return false;
    }

    @Override
    public void getCards() {
        if(cards == null){
            this.view.showLoading();
            ConsultaBaseRequestVO requestVO = new ConsultaBaseRequestVO();
            requestVO.page      = 1;
            requestVO.pageSize  = 100;
            this.cardsService.getCards(this, requestVO);
        }
    }

    @Override
    public void enviaNovaTransacao(String valor, int parcelas) {

        this.valor      = valor;
        this.parcelas   = parcelas;

        this.view.showLoading();

        valor = valor.replaceAll("[,.]", "");

        long val = Long.parseLong(valor);

        if(validaValor(val)){
            NovaTransacaoRequestVO requestVO = new NovaTransacaoRequestVO();

            if(cards != null){
                Card card = this.cards.getRadonCard();
                requestVO.cardId = card.getCardId();
            }

            requestVO.parcelas  = parcelas+1;
            requestVO.valor     = val;

            service.novaTransacao(this, requestVO);
        }else{
            this.view.hideLoading();
            this.view.alertaValorZerado();
        }

    }

    @Override
    public void onSuccess(boolean isCreated) {
        this.view.hideLoading();
        this.view.showConfirmacao();
    }

    @Override
    public void onError(ApiResponseType error) {
        this.view.hideLoading();
        this.view.showEptyState(error);
    }

    @Override
    public void onApiError(ApiResponseType error) {
        this.view.hideLoading();
        this.view.showEptyState(error);
    }

    @Override
    public void onCardsSuccess(Cards cards) {
        this.view.hideLoading();
        this.cards = cards;
    }
}
