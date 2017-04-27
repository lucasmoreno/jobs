package br.com.testepaggi.ui.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.testepaggi.R;
import br.com.testepaggi.model.ApiResponseType;
import br.com.testepaggi.ui.presenter.PagamentoPresenter;
import br.com.testepaggi.ui.presenter.impl.PagamentoPresenterImpl;
import br.com.testepaggi.ui.view.PagamentoView;
import br.com.testepaggi.util.Utils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class PagamentosActivity extends BaseActivity implements PagamentoView {

    @Bind(R.id.rvTransacaoes) RecyclerView recycler;

    private PagamentoPresenter  presenter;
    private LinearLayoutManager mLayoutManager;
    private int                 itensInitial = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamentos);

        ButterKnife.bind(this);

        setupNavigateActionBar(R.string.screen_pagamento);

        presenter = new PagamentoPresenterImpl(this);

        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private boolean loading = true;
            int pastVisiblesItems, visibleItemCount, totalItemCount;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if(dy > 0){

                    visibleItemCount    = mLayoutManager.getChildCount();
                    totalItemCount      = mLayoutManager.getItemCount();
                    pastVisiblesItems   = mLayoutManager.findFirstVisibleItemPosition();

                    if(totalItemCount > itensInitial){
                        loading = true;
                        itensInitial = totalItemCount;
                    }

                    if (loading){

                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount){
                            loading = false;
                            presenter.resgataPagamentosComPaginacao();
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.isNetworkAvailable()){
            if(recycler.getVisibility() == View.GONE){
                presenter.tentarNovament();
                recycler.setVisibility(View.VISIBLE);
                emptyStateContainer.setVisibility(View.GONE);
            }
        }
    }

    /**
     * Carrega o recycler view
     * <p>
     *     Metodo usado para para configurar e preparar o recycler view para receber as informações do adapter
     * </p>
     *
     */
    @Override
    public void carregaAdapter() {
        recycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(mLayoutManager);
    }

    /**
     * Exibir dados do recycler view
     * <p>
     *     Metodo usado para setar o adapter ao recycler e exibir a lista de resultados
     * </p>
     *
     * @param adapter   adapter com as informações montadas
     */
    @Override
    public void mostrarAdapter(RecyclerView.Adapter adapter) {
        if(recycler.getVisibility() == View.GONE){
            recycler.setVisibility(View.VISIBLE);
            emptyStateContainer.setVisibility(View.GONE);
        }
        recycler.setAdapter(adapter);
    }

    @Override
    public void showEptyState(ApiResponseType error) {
        recycler.setVisibility(View.GONE);
        super.showEptyState(error);
    }

    /**
     * Tentar novamente
     * <p>
     *     Metodo usado para reenviar uma ação após um erro do app
     * </p>
     *
     */
    @Nullable
    @OnClick(R.id.btnReTry)
    public void tentarNovamente(){
        emptyStateContainer.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);
        presenter.tentarNovament();
    }

}
