package br.com.testepaggi.ui.view;

import android.content.Context;
import android.content.Intent;

import br.com.testepaggi.model.ApiResponseType;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface BaseView {

    void setupNavigateActionBar(int resId);

    void setupTransparentNavigateActionBar(int resId);

    void setupNavigateActionBarModal(int resId);

    void showLoading();

    void hideLoading();

    void showEptyState(ApiResponseType error);

    Context getContext();

    void onBackPressed();

    void showModal(Intent intent);

}
