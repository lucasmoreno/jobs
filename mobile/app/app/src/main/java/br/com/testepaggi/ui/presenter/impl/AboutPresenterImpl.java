package br.com.testepaggi.ui.presenter.impl;


import br.com.testepaggi.R;
import br.com.testepaggi.business.service.AboutService;
import br.com.testepaggi.business.service.impl.AboutServiceImpl;
import br.com.testepaggi.common.AboutResultListener;
import br.com.testepaggi.ui.presenter.AboutPresenter;
import br.com.testepaggi.ui.view.AboutView;
import br.com.testepaggi.util.StringUtils;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class AboutPresenterImpl implements AboutPresenter, AboutResultListener {

    private AboutView view;
    private AboutService service;

    public AboutPresenterImpl(AboutView view) {
        this.view = view;
        init();
    }

    @Override
    public void init() {
        view.showLoading();
        service = new AboutServiceImpl();
        service.getConfigInfos(this);
    }

    @Override
    public void setCurrentYear(String date) {
        String dateInfo = StringUtils.setValue(view.getContext().getString(R.string.about_rights), date);
        this.view.showCurrentYear(dateInfo);
    }

    @Override
    public void setCurrentVersionCode(String version) {
        this.view.showVersionCode(version);
    }

    @Override
    public void onSuccess(String version, String date) {
        setCurrentVersionCode(version);
        setCurrentYear(date);
        view.hideLoading();
    }

}
