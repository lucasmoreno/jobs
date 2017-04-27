package br.com.testepaggi.ui.view.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import br.com.testepaggi.R;
import br.com.testepaggi.model.ApiResponseType;
import br.com.testepaggi.ui.adapter.ParcelasAdapter;
import br.com.testepaggi.ui.presenter.NovaTransacaoPresenter;
import br.com.testepaggi.ui.presenter.impl.NovaTransacaoPresenterImpl;
import br.com.testepaggi.ui.view.NovaTransacaoView;
import br.com.testepaggi.ui.view.component.CurrencyFormat;
import br.com.testepaggi.util.Utils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class NovaTransacaoActivity extends BaseActivity implements NovaTransacaoView {

    @Bind(R.id.spParcelas)      Spinner     spParcelas;
    @Bind(R.id.edtValor)        EditText    edtValor;
    @Bind(R.id.txValorError)    TextView    txValorError;
    @Bind(R.id.scrollView)      NestedScrollView scrollView;

    private NovaTransacaoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_transacao);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        ButterKnife.bind(this);

        setupNavigateActionBarModal(R.string.screen_nova_transacao);

        presenter = new NovaTransacaoPresenterImpl(this);

        String[] parcelas = getResources().getStringArray(R.array.parcelas);

        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        spParcelas.setLayoutParams(lp1);
        spParcelas.setAdapter(new ParcelasAdapter(this, parcelas));

        edtValor.addTextChangedListener(new CurrencyFormat(edtValor));
    }

    @Override
    protected void onResume() {
        if(Utils.isNetworkAvailable()){
            scrollView.setVisibility(View.VISIBLE);
            emptyStateContainer.setVisibility(View.GONE);
            presenter.getCards();
        }
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.close_option, menu);

        Drawable drawable = menu.getItem(0).getIcon();
        if(drawable != null) {
            drawable.mutate();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                drawable.setColorFilter(getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
            }else{
                drawable.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_fechar){
            finish();
            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top);
        }

        if(item.getItemId() == android.R.id.home){
            presenter.enviaNovaTransacao(edtValor.getText().toString(), spParcelas.getSelectedItemPosition());
        }

        return true;
    }

    @Override
    public void onBackPressed() {}

    /**
     * Confirmação
     * <p>
     *     Metodo usado para exibir a mensagem de que o valor é menor ou igual a zero
     * </p>
     *
     */
    @Override
    public void alertaValorZerado() {
        Toast.makeText(this, getString(R.string.novatransacao_valor_zerado), Toast.LENGTH_LONG).show();
    }

    /**
     * Confirmação
     * <p>
     *     Metodo usado para exibir a mensagem de sucesso caso a transação seja inserida com sucesso
     * </p>
     *
     */
    @Override
    public void showConfirmacao() {
        Toast.makeText(this, getString(R.string.novatransacao_sucesso), Toast.LENGTH_LONG).show();

        finish();
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top);
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
        scrollView.setVisibility(View.VISIBLE);
        presenter.tentarNovament();
    }

    /**
     * Limpar e tentar novamente
     * <p>
     *     Metodo usado para limpar e inserir novos dados da nova transacao
     * </p>
     *
     */
    @Nullable
    @OnClick(R.id.btnNovaTransacao)
    public void novaTentativa(){
        emptyStateContainer.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
        spParcelas.setSelection(0);
        edtValor.setText("0.00");
        edtValor.requestFocus();
    }

    @Override
    public void showEptyState(ApiResponseType error) {
        scrollView.setVisibility(View.GONE);
        super.showEptyState(error);
    }
}
