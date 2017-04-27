package br.com.testepaggi.model;

import java.io.Serializable;
import java.util.Date;

import br.com.testepaggi.business.api.vo.response.PagamentoItemResponseVO;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class Pagamento implements Serializable{

    public static String KEY = Pagamento.class.getSimpleName();

    private StatusItens status;
    private int     valor;
    private Date    compensado;
    private Date    compensacao;

    public Pagamento(PagamentoItemResponseVO responseVO) {
        this.valor          = responseVO.valor;
        this.compensado     = responseVO.compensado;
        this.compensacao    = responseVO.compensacao;

        this.setStatusItem(responseVO.status);
    }

    private void setStatusItem(String status){
        this.status = StatusItens.getItemStatus(status);
    }

    public StatusItens getStatus() {
        return status;
    }

    public void setStatus(StatusItens status) {
        this.status = status;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Date getCompensado() {
        return compensado;
    }

    public void setCompensado(Date compensado) {
        this.compensado = compensado;
    }

    public Date getCompensacao() {
        return compensacao;
    }

    public void setCompensacao(Date compensacao) {
        this.compensacao = compensacao;
    }
}
