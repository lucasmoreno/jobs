package br.com.testepaggi.business.api;

import br.com.testepaggi.business.api.vo.request.NovaTransacaoRequestVO;
import br.com.testepaggi.business.api.vo.response.NovaTransacaoResponseVO;
import br.com.testepaggi.business.api.vo.response.PagamentoResponseVO;
import br.com.testepaggi.business.api.vo.response.TransacaoResponseVO;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface ServiceApi {

    @POST("charges")
    Call<NovaTransacaoResponseVO> addCharge(@Body NovaTransacaoRequestVO requestVO);

    @GET("getcharges/?page={page}&page_size={pageSize}")
    Call<TransacaoResponseVO> transacoes(
            @Path("page")       int page,
            @Path("pageSize")   int pageSize
    );

    @GET("getcharges/?page={page}&page_size={pageSize}&start_date={start}&end_date={end}")
    Call<TransacaoResponseVO> transacoes(
            @Path("page")       int page,
            @Path("pageSize")   int pageSize,
            @Path("start")      String start,
            @Path("end")        String end
    );

    @GET("payments/?page={page}&page_size={pageSize}")
    Call<PagamentoResponseVO> pagamentos(
            @Path("page")       int page,
            @Path("pageSize")   int pageSize
    );

    @GET("getcharges/?page={page}&page_size={pageSize}&start_date={start}&end_date={end}")
    Call<PagamentoResponseVO> pagamentos(
            @Path("page")       int page,
            @Path("pageSize")   int pageSize,
            @Path("start")      String start,
            @Path("end")        String end
    );
}
