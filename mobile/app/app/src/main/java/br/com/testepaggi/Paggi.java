package br.com.testepaggi;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class Paggi extends MultiDexApplication {

    private static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }

    /**
     * Recupera o Context da Aplicação
     * <p>
     *     Metodo usado para recuperar o context geral da aplicação
     * </p>
     *
     * @return      Context  context da aplicação
     * @see         Context
     */
    public static Context getContext() {
        return context;
    }


    /**
     * Seta o Context da Aplicação
     * <p>
     *     Metodo usado para setar o context geral da aplicação
     * </p>
     *
     * @param   context context da aplicação
     */
    public static void setContext(Context context) {
        Paggi.context = context;
    }
}
