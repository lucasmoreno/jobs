package br.com.testepaggi.ui.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import br.com.testepaggi.R;
import br.com.testepaggi.ui.presenter.AboutPresenter;
import br.com.testepaggi.ui.presenter.impl.AboutPresenterImpl;
import br.com.testepaggi.ui.view.AboutView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class SobreActivity extends BaseActivity implements AboutView {

    @Bind(R.id.txVersion)   TextView txVersion;
    @Bind(R.id.txRights)    TextView txRights;

    private AboutPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        ButterKnife.bind(this);

        setupTransparentNavigateActionBar(R.string.screen_sobre);

        presenter = new AboutPresenterImpl(this);

    }

    @Override
    public void showCurrentYear(String date) {
        txRights.setText(date);
    }

    @Override
    public void showVersionCode(String version) {
        txVersion.setText(version);
    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }

}
