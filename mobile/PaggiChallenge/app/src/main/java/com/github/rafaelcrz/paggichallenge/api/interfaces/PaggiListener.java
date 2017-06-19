package com.github.rafaelcrz.paggichallenge.api.interfaces;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Rafael Felipe on 13/06/2017.
 *
 * Listeners de cada chamada da Api.
 * Existem diversos tipos de chamadas, nesse caso precisa ser passado qual o tipo
 * do Call.
 */

public interface PaggiListener<T> {

    void onResponde(Call<T> call, Response<T> response);
    void onFailure(Call<T> call, Throwable throwable);
}
