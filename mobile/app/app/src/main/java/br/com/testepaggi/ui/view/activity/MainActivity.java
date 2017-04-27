package br.com.testepaggi.ui.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.testepaggi.R;
import br.com.testepaggi.model.ApiResponseType;
import br.com.testepaggi.ui.view.MainActivityView;
import br.com.testepaggi.util.Utils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainActivityView {

    @Bind(R.id.drawer_layout)   DrawerLayout     drawer;
    @Bind(R.id.nav_view)        NavigationView   navigationView;
    @Bind(R.id.scrollView)      NestedScrollView scrollView;

    private TextView txPhone;
    private TextView txEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setTitle(R.string.screen_dashboard);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        txPhone = (TextView) headerView.findViewById(R.id.txPhone);
        txEmail = (TextView) headerView.findViewById(R.id.txEmail);

        txPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                peformClickOnPhone();
            }
        });

        txEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                peformClickOnEmail();
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_novo:
                peformClickOnNewCharge();
                break;
            case R.id.nav_transaction:
                peformClickOnTransaction();
                break;
            case R.id.nav_payments:
                peformClickOnPayments();
                break;
            case R.id.nav_about:
                peformClickOnAbout();
                break;
            default:
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Abro opções para ligar ou enviar sms
     * <p>
     *     Metodo usado para o usuário fazer uma ligação ou enviar um sms
     * </p>
     *
     */
    @Override
    public void peformClickOnPhone() {
        //TODO:: abrir a caixa de dialog para ligação
    }

    /**
     * Abro opções para enviar um email
     * <p>
     *     Metodo usado para o usuário enviar um email
     * </p>
     *
     */
    @Override
    public void peformClickOnEmail() {
        //TODO:: abrir a caixa de dialog para email
    }

    /**
     * Navegar para transações
     * <p>
     *     Metodo usado para enviar o usuário para a tela de transações
     * </p>
     *
     */
    @OnClick(R.id.containerTransacao)
    @Override
    public void peformClickOnTransaction() {
        startActivity(new Intent(this, TransacaoActivity.class));
    }

    /**
     * Navegar para pagamentos
     * <p>
     *     Metodo usado para enviar o usuário para a tela de pagamentos
     * </p>
     *
     */
    @OnClick(R.id.containerPagamentos)
    @Override
    public void peformClickOnPayments() {
        startActivity(new Intent(this, PagamentosActivity.class));
    }

    /**
     * Navegar para a nova transação
     * <p>
     *     Metodo usado para enviar o usuário para a tela de nova transação
     * </p>
     *
     */
    @OnClick(R.id.containerNovaTransacao)
    @Override
    public void peformClickOnNewCharge() {
        showModal(new Intent(this, NovaTransacaoActivity.class));
    }

    /**
     * Navegar para a tela sobre
     * <p>
     *     Metodo usado para enviar o usuário para a tela de sobre o app
     * </p>
     *
     */
    @Override
    public void peformClickOnAbout() {
        startActivity(new Intent(this, SobreActivity.class));
    }

}
