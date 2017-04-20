package br.com.testepaggi.ui.view;

import android.content.Context;
import android.content.Intent;

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

//    void showDialogError(ApiResponse error);

    Context getContext();

    void onBackPressed();

    void showModal(Intent intent);

}
