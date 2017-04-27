package br.com.testepaggi.business.service.impl;

import java.util.Calendar;

import br.com.testepaggi.BuildConfig;
import br.com.testepaggi.business.service.AboutService;
import br.com.testepaggi.common.AboutResultListener;
import br.com.testepaggi.util.DateUtils;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class AboutServiceImpl implements AboutService {

    @Override
    public void getConfigInfos(AboutResultListener listener) {

        Calendar calendar   = Calendar.getInstance();
        listener.onSuccess(BuildConfig.VERSION_NAME, DateUtils.getYearFromDate(calendar.getTime()));
    }

}
