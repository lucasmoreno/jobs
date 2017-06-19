package com.github.rafaelcrz.paggichallenge.application;

import android.app.Application;

import com.github.rafaelcrz.paggichallenge.api.interfaces.PaggiService;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by Rafael Felipe on 13/06/2017.
 * <p>
 * A biblioteca Jodatime precisa ser inicializada em Application, para ter acesso contante ao broadcast de timezone,
 * caso o usuario sai de um timezone para outro (viagem), o Jodatime pode se adequar.
 */

public class PaggiApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        JodaTimeAndroid.init(this);
    }

}
