package br.com.testepaggi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.testepaggi.business.api.vo.response.TransacaoItemResponseVO;
import br.com.testepaggi.business.api.vo.response.TransacaoResponseVO;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class Transacoes implements Serializable {

    private static final String KEY = Transacoes.class.getSimpleName();
    private int total;
    private List<Transacao> trasancoes;

    public Transacoes(TransacaoResponseVO responseVo) {
        this.total      = responseVo.total;
        this.trasancoes = new ArrayList<>();
        geraTransacoes(responseVo);
    }

    private void geraTransacoes(TransacaoResponseVO responseVO){
        for (TransacaoItemResponseVO response : responseVO.result){
            this.trasancoes.add(new Transacao(response));
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Transacao> getTrasancoes() {
        return trasancoes;
    }

    public void setTrasancoes(List<Transacao> trasancoes) {
        this.trasancoes = trasancoes;
    }

}
