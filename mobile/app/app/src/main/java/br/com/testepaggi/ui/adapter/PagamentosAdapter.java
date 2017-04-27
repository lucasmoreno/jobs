package br.com.testepaggi.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;

import br.com.testepaggi.R;
import br.com.testepaggi.model.Pagamento;
import br.com.testepaggi.model.Pagamentos;
import br.com.testepaggi.model.Transacao;
import br.com.testepaggi.model.Transacoes;
import br.com.testepaggi.util.DateUtils;
import br.com.testepaggi.util.StringUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class PagamentosAdapter extends RecyclerView.Adapter<PagamentosAdapter.ViewHolder> {

    private Pagamentos  pagamentos;
    private Context     context;

    public PagamentosAdapter(Pagamentos pagamentos, Context context) {
        this.pagamentos = pagamentos;
        this.context    = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(context).inflate(R.layout.row_pagamento_timeline, parent, false);
        return new ViewHolder(itemLayoutView);
    }

    public void addItem(Pagamento pagamento){
        this.pagamentos.getPagamentos().add(pagamento);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pagamento pagamento = pagamentos.getPagamentos().get(position);

        if(pagamento != null){

            if(pagamento.getCompensado() != null){
                holder.containerCompensacao.setVisibility(View.GONE);
                holder.txCompensado.setText(DateUtils.parseDateToString(pagamento.getCompensado()));
            }else{
                holder.containerCompensacao.setVisibility(View.VISIBLE);
                holder.containerCompensado.setVisibility(View.GONE);
                holder.txCompensacao.setText(DateUtils.parseDateToString(pagamento.getCompensacao()));
            }

            BigDecimal parsed;

            try{
                parsed = new BigDecimal(String.valueOf(pagamento.getValor())).setScale(2, BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);
            }catch (Exception e){
                parsed = new BigDecimal(0.00);
                e.printStackTrace();
            }

            holder.txValor.setText(StringUtils.doubleToCurrency(parsed.doubleValue()));

            holder.txStatus.setText(pagamento.getStatus().getStatus());
            holder.imgStatus.setImageResource(pagamento.getStatus().getIconResource());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.txStatus.setTextColor(context.getColor(pagamento.getStatus().getColorIcon()));
                holder.imgStatus.setColorFilter(context.getColor(pagamento.getStatus().getColorIcon()));
            }else{
                holder.txStatus.setTextColor(context.getResources().getColor(pagamento.getStatus().getColorIcon()));
                holder.imgStatus.setColorFilter(context.getResources().getColor(pagamento.getStatus().getColorIcon()));
            }

        }
    }

    @Override
    public int getItemCount() {
        return pagamentos.getPagamentos().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.txValor)         TextView    txValor;
        @Bind(R.id.txCompensado)    TextView    txCompensado;
        @Bind(R.id.txCompensacao)   TextView    txCompensacao;
        @Bind(R.id.txStatus)        TextView    txStatus;
        @Bind(R.id.imgStatus)       ImageView   imgStatus;

        @Bind(R.id.containerCompensado)     LinearLayout containerCompensado;
        @Bind(R.id.containerCompensacao)    LinearLayout containerCompensacao;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
