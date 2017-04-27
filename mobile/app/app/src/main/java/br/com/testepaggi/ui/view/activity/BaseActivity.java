package br.com.testepaggi.ui.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.testepaggi.R;
import br.com.testepaggi.model.ApiResponseType;
import br.com.testepaggi.ui.view.component.ProgressDialog;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @Bind(R.id.toolbar) Toolbar toolbar;

    //Empty State
    @Nullable
    @Bind(R.id.emptyStateContainer) LinearLayout emptyStateContainer;
    @Nullable
    @Bind(R.id.iconError)   ImageView   iconError;
    @Nullable
    @Bind(R.id.txError)     TextView    txError;
    @Nullable
    @Bind(R.id.btnReTry)    Button      btnReTry;
    @Nullable
    @Bind(R.id.btnConectar) Button      btnConectar;
    @Nullable
    @Bind(R.id.btnNovaTransacao) Button      btnNovaTransacao;

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

    /**
     * Montar toolbar comum
     * <p>
     *     Metodo usado para exibir um toolbar comum
     * </p>
     *
     * @param resId resource id da string do titulo do modal
     */
    public void setupNavigateActionBar(int resId) {

        setTitle(resId);

        setSupportActionBar(toolbar);

        this.actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    /**
     * Montar toolbar transparente
     * <p>
     *     Metodo usado para exibir um toolbar exclusivamente para modal
     * </p>
     *
     * @param resId resource id da string do titulo do modal
     */
    public void setupTransparentNavigateActionBar(int resId) {

        setTitle(resId);

        setSupportActionBar(toolbar);

        this.actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    /**
     * Montar toolbar para o modal
     * <p>
     *     Metodo usado para exibir um toolbar exclusivamente para modal
     * </p>
     *
     * @param resId resource id da string do titulo do modal
     */
    public void setupNavigateActionBarModal(int resId) {

        setTitle(resId);

        Drawable icon;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            icon = getDrawable( R.drawable.ic_check_white_24dp );
        }else{
            icon = getResources().getDrawable( R.drawable.ic_check_white_24dp );
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            icon.setColorFilter(getColor(R.color.green), PorterDuff.Mode.SRC_ATOP);
        }else{
            icon.setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setNavigationIcon(icon);

        setSupportActionBar(toolbar);

        this.actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    /**
     * Exibir loading
     * <p>
     *     Metodo usado para exibir o loading da aplicação
     * </p>
     */
    public void showLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            hideLoading();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.show();
    }

    /**
     * Esconder loading
     * <p>
     *     Metodo usado para esconder o loading da aplicação
     * </p>
     */
    public void hideLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    /**
     * Exibir empty state
     * <p>
     *     Metodo usado para exibir o empty state para dar um feedback para o usuário em caso de erros
     * </p>
     *
     * @param error ApiResponseType a ser exibiddo
     */
    public void showEptyState(ApiResponseType error){
        emptyStateContainer.setVisibility(View.VISIBLE);
        iconError.setImageResource(error.getIconResId());
        txError.setText(getString(error.getStrResId()));


        if(!error.isTentarNovamente()){
            btnReTry.setVisibility(View.GONE);
        }else{
            btnReTry.setVisibility(View.VISIBLE);
        }

        if(error == ApiResponseType.NO_INTENET_CONNECTION){
            btnConectar.setVisibility(View.VISIBLE);
        }else{
            btnConectar.setVisibility(View.GONE);
        }

        if(error == ApiResponseType.ERROR_INSERT_TRANSACAO){
            btnNovaTransacao.setVisibility(View.VISIBLE);
        }else{
            btnNovaTransacao.setVisibility(View.GONE);
        }

    }

    /**
     * Recupera o Context da Activity
     * <p>
     *     Metodo usado para recuperar o context da Activity
     * </p>
     *
     * @return      Context  context da Activity
     * @see         Context
     */
    public Context getContext(){
        return getApplicationContext();
    }

    /**
     * Exibe um modal
     * <p>
     *     Metodo usado para exibir uma activity com a animação de modal
     * </p>
     *
     * @param   intent Intent com os parametros para exibir a activity como modal
     */
    public  void  showModal(Intent intent){
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top);
    }

    /**
     * Conectar
     * <p>
     *     Metodo usado para abrir as configurações do app e ativar as conexões de rede
     * </p>
     */
    @Nullable
    @OnClick(R.id.btnConectar)
    public void conectar(){
        startActivity(new Intent(Settings.ACTION_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK));
    }

}