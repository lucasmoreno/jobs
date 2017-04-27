package br.com.testepaggi.business.api.vo.request;

import com.google.gson.annotations.SerializedName;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class NovaTransacaoRequestVO {
    @SerializedName("amount")               public long     valor;
    @SerializedName("card_id")              public String   cardId;
    @SerializedName("installments_number")  public int      parcelas;
}
