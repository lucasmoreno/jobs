package br.com.testepaggi.model;

import java.io.Serializable;
import java.util.Date;

import br.com.testepaggi.business.api.vo.response.TransacaoItemResponseVO;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class Transacao implements Serializable{

    public static String KEY = Transacao.class.getSimpleName();

    private StatusItens status;
    private int         valor;
    private Date        compensacao;
    private Date        criacao;

    public Transacao(TransacaoItemResponseVO responseVO) {
        this.valor          = responseVO.valor;
        this.compensacao    = responseVO.compensacao;
        this.criacao        = responseVO.criacao;
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

    public Date getCompensacao() {
        return compensacao;
    }

    public void setCompensacao(Date compensacao) {
        this.compensacao = compensacao;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }
}
