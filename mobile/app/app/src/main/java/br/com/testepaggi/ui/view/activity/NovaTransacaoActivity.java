package br.com.testepaggi.ui.view.activity;

import android.os.Bundle;

import br.com.testepaggi.R;
import butterknife.ButterKnife;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class NovaTransacaoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_transacao);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        ButterKnife.bind(this);

        setupNavigateActionBarModal(R.string.screen_transacao);
    }
}
