package br.com.testepaggi.ui.view.activity;

import android.os.Bundle;

import br.com.testepaggi.R;
import butterknife.ButterKnife;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class TransacaoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacao);

        ButterKnife.bind(this);

        setupNavigateActionBar(R.string.screen_transacao);
    }
}
