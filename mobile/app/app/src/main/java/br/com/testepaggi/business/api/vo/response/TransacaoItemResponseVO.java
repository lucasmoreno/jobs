package br.com.testepaggi.business.api.vo.response;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class TransacaoItemResponseVO {

    @SerializedName("status")                   public String   status;
    @SerializedName("amount")                   public int      valor;
    @SerializedName("expected_compensation")    public Date     compensacao;
    @SerializedName("created")                  public Date     criacao;
}
