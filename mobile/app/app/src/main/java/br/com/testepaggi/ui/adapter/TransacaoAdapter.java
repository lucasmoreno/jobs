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

public class TransacaoAdapter extends RecyclerView.Adapter<TransacaoAdapter.ViewHolder> {

    private Transacoes  transacoes;
    private Context     context;

    public TransacaoAdapter(Transacoes transacoes, Context context) {
        this.transacoes = transacoes;
        this.context    = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(context).inflate(R.layout.row_transacao_timeline, parent, false);
        return new ViewHolder(itemLayoutView);
    }

    public void addItem(Transacao transacao){
        this.transacoes.getTrasancoes().add(transacao);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transacao transacao = transacoes.getTrasancoes().get(position);

        if(transacao != null){

            holder.txData.setText(DateUtils.parseDateToString(transacao.getCriacao()));

            if(transacao.getCompensacao() == null){
                holder.containerCompensacao.setVisibility(View.GONE);
            }else{
                holder.containerCompensacao.setVisibility(View.VISIBLE);
                holder.txCompensacao.setText(DateUtils.parseDateToString(transacao.getCompensacao()));
            }

            BigDecimal parsed;

            try{
                parsed = new BigDecimal(String.valueOf(transacao.getValor())).setScale(2, BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);
            }catch (Exception e){
                parsed = new BigDecimal(0.00);
                e.printStackTrace();
            }

            holder.txValor.setText(StringUtils.doubleToCurrency(parsed.doubleValue()));
            holder.txStatus.setText(transacao.getStatus().getStatus());
            holder.imgStatus.setImageResource(transacao.getStatus().getIconResource());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.txStatus.setTextColor(context.getColor(transacao.getStatus().getColorIcon()));
                holder.imgStatus.setColorFilter(context.getColor(transacao.getStatus().getColorIcon()));
            }else{
                holder.txStatus.setTextColor(context.getResources().getColor(transacao.getStatus().getColorIcon()));
                holder.imgStatus.setColorFilter(context.getResources().getColor(transacao.getStatus().getColorIcon()));
            }

        }
    }

    @Override
    public int getItemCount() {
        return transacoes.getTrasancoes().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.txData)          TextView    txData;
        @Bind(R.id.txValor)         TextView    txValor;
        @Bind(R.id.txCompensacao)   TextView    txCompensacao;
        @Bind(R.id.txStatus)        TextView    txStatus;
        @Bind(R.id.imgStatus)       ImageView   imgStatus;

        @Bind(R.id.containerCompensacao)    LinearLayout containerCompensacao;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
