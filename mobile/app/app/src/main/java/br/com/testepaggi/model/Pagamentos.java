package br.com.testepaggi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.testepaggi.business.api.vo.response.PagamentoItemResponseVO;
import br.com.testepaggi.business.api.vo.response.PagamentoResponseVO;
import br.com.testepaggi.business.api.vo.response.TransacaoItemResponseVO;
import br.com.testepaggi.business.api.vo.response.TransacaoResponseVO;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class Pagamentos implements Serializable {

    private static final String KEY = Pagamentos.class.getSimpleName();
    private int total;
    private List<Pagamento> pagamentos;

    public Pagamentos(PagamentoResponseVO responseVo) {
        this.total      = responseVo.total;
        this.pagamentos = new ArrayList<>();
        geraPagamentos(responseVo);
    }

    private void geraPagamentos(PagamentoResponseVO responseVO){
        for (PagamentoItemResponseVO response : responseVO.result){
            this.pagamentos.add(new Pagamento(response));
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }
}
