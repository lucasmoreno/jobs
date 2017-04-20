package br.com.testepaggi.ui.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.testepaggi.R;
import butterknife.Bind;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @Bind(R.id.toolbar) Toolbar toolbar;

    public ActionBar        actionBar;
    private ProgressDialog  mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:

                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setupNavigateActionBar(int resId) {

        setTitle(resId);

        setSupportActionBar(toolbar);

        this.actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public void setupTransparentNavigateActionBar(int resId) {

        setTitle(resId);

        setSupportActionBar(toolbar);

        this.actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public void setupNavigateActionBarModal(int resId) {

        setTitle(resId);

        setSupportActionBar(toolbar);

        this.actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

    }

    public void showLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            hideLoading();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.show();
    }

    public void hideLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public Context getContext(){
        return getApplicationContext();
    }

    public  void  showModal(Intent intent){
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top);
    }

}
