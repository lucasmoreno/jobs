package br.com.testepaggi.business.api;

import br.com.testepaggi.business.api.vo.request.NovaTransacaoRequestVO;
import br.com.testepaggi.business.api.vo.response.CardsResponseVO;
import br.com.testepaggi.business.api.vo.response.NovaTransacaoResponseVO;
import br.com.testepaggi.business.api.vo.response.PagamentoResponseVO;
import br.com.testepaggi.business.api.vo.response.TransacaoResponseVO;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface ServiceApi {

    @POST("charges")
    Call<NovaTransacaoResponseVO> addCharge(@Body NovaTransacaoRequestVO requestVO);

    @GET("charges/")
    Call<TransacaoResponseVO> transacoes(
            @Query("page")          int page,
            @Query("page_size")     int pageSize,
            @Query("start_date")    String start,
            @Query("end_date")      String end
    );

    @GET("payments/")
    Call<PagamentoResponseVO> pagamentos(
            @Query("page")          int page,
            @Query("page_size")     int pageSize,
            @Query("start_date")    String start,
            @Query("end_date")      String end
    );

    @GET("cards")
    Call<CardsResponseVO> cards(
            @Query("page")          int page,
            @Query("page_size")     int pageSize
    );
}
