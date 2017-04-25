package br.com.testepaggi.ui.view.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import br.com.testepaggi.Paggi;
import br.com.testepaggi.R;
import br.com.testepaggi.ui.adapter.ParcelasAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class NovaTransacaoActivity extends BaseActivity {

    @Bind(R.id.spParcelas)    Spinner spParcelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_transacao);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        ButterKnife.bind(this);

        setupNavigateActionBarModal(R.string.screen_nova_transacao);

        String[] parcelas = getResources().getStringArray(R.array.parcelas);

        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        spParcelas.setLayoutParams(lp1);
        spParcelas.setAdapter(new ParcelasAdapter(this, parcelas));
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
            //TODO:: implementar a rotina para inserir uma nova transação
        }

        return true;
    }

    @Override
    public void onBackPressed() {}
}
