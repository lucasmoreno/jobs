package br.com.testepaggi.ui.view;

import android.support.v7.widget.RecyclerView;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface PagamentoView extends BaseView{

    void carregaAdapter();

    void mostrarAdapter(RecyclerView.Adapter adapter);
}
