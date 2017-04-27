package br.com.testepaggi.business.api.vo.response;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class PagamentoItemResponseVO {

    @SerializedName("status")                   public String   status;
    @SerializedName("amount")                   public int      valor;
    @SerializedName("compensated_at")           public Date     compensado;
    @SerializedName("expected_compensation")    public Date     compensacao;
}
