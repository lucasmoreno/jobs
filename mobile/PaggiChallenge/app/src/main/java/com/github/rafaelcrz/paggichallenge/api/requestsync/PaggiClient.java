package com.github.rafaelcrz.paggichallenge.api.requestsync;

import android.content.Context;
import android.util.Base64;

import com.github.rafaelcrz.paggichallenge.api.interfaces.PaggiService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rafael Felipe on 13/06/2017.
 * <p>
 * Classe que retorna um Cliente com as configuraççoes da requisição
 */

public class PaggiClient {

    private static Retrofit retrofit;
    private static int cacheSize = 10 * 1024 * 1024;//Configuração de cache

    public static Retrofit getPaggiClient(Context context) {

        if (retrofit == null) {
            //cache
            Cache cache = new Cache(context.getCacheDir(), cacheSize);

            //Builder do okhttp
            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
            okHttpClient.cache(cache);
            //Interceptor com a contrução dos headers
            okHttpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            //Basic Http Auth
                            .header("Authorization", "Basic " + Base64.encodeToString("B31DCE74-E768-43ED-86DA-85501612548F".getBytes(), Base64.NO_WRAP))
                            .method(original.method(), original.body())
                            .build();
                    Response response = chain.proceed(request);
                    return response;
                }
            });
            retrofit = new Retrofit.Builder()
                    .baseUrl(PaggiService.BASE_URL)
                    .client(okHttpClient.build())
                    //Gson para o parse
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
