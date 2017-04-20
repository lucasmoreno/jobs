package br.com.testepaggi.business.api;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import br.com.testepaggi.BuildConfig;
import br.com.testepaggi.Paggi;
import br.com.testepaggi.R;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class ApiClient extends OkHttpClient {

    private static ApiClient instance;
    private final static long TIMEOUT = Paggi.getContext()
            .getResources().getInteger(R.integer.default_api_timeout);

    private ApiClient(){};

    public static ApiClient getInstance(){

        if (instance == null){
            instance = new ApiClient();

            instance.interceptors().add(getHeaderInterceptor());

            if (BuildConfig.DEBUG) {
                instance.interceptors().add(getLogInterceptor());
            }

            instance.setConnectTimeout(TIMEOUT, TimeUnit.SECONDS);
            instance.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
            instance.setWriteTimeout(TIMEOUT, TimeUnit.SECONDS);
        }
        return instance;
    }

    private static Interceptor getHeaderInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                original.urlString();
                Request.Builder builder = original.newBuilder();
                builder.addHeader("Content-Type", "application/json; charset=UTF-8");

                builder.addHeader("Authorization", Paggi.getContext().getString(R.string.token));

                builder.method(original.method(), original.body());

                return chain.proceed(builder.build());
            }
        };
    }

    private static HttpLoggingInterceptor getLogInterceptor(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override public void log(String message) {
                        Log.d("OkHttp", message);
                    }
                });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

}
