package com.github.rafaelcrz.paggichallenge.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.rafaelcrz.paggichallenge.R;
import com.github.rafaelcrz.paggichallenge.api.models.Transaction;
import com.github.rafaelcrz.paggichallenge.util.PaggiFormatterUtil;

import java.util.List;


public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<Transaction> transactioList;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public TransactionAdapter(Context c, List<Transaction> transactioList) {
        this.mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.transactioList = transactioList;
        this.context = c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_transaction, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int position) {

        Transaction transaction = transactioList.get(position);

        String compensated_at = PaggiFormatterUtil.formatStringDate(context, transaction.getExpected_compensation(), PaggiFormatterUtil.WITH_SECOND_PATTERN);

        String amount = null;
        try {
            amount = PaggiFormatterUtil.currencyFormatter(transaction.getAmount());
        } catch (Exception e) {
            amount = String.valueOf(transaction.getAmount());
            e.printStackTrace();
        }
        String status = transaction.getStatus();

        myViewHolder.tvDescr.setText(transaction.getStatement_descriptor());
        myViewHolder.tvDateCompensated.setText(compensated_at);
        myViewHolder.tvDateCreated.setText(transaction.getCreated_at());

        myViewHolder.tvStatus.setText(status);
        myViewHolder.tvAmount.setText("$ " + amount);


    }

    @Override
    public int getItemCount() {
        try {
            return this.transactioList.size();
        } catch (Exception e) {
            return 0;
        }
    }


    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        mRecyclerViewOnClickListenerHack = r;
    }

    public void addItem(Transaction transaction, int position) {
        transactioList.add(transaction);
        notifyItemInserted(position);
    }

    public void clear() {
        transactioList.clear();
        notifyDataSetChanged();
    }

    public List<Transaction> getList() {
        return transactioList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvAmount, tvDateCompensated, tvDateCreated, tvStatus, tvDescr;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvDescr = (TextView) itemView.findViewById(R.id.tvItemChargeDesc);
            tvAmount = (TextView) itemView.findViewById(R.id.tvItemChargeAmount);
            tvDateCompensated = (TextView) itemView.findViewById(R.id.tvItemChargeCompensa);
            tvStatus = (TextView) itemView.findViewById(R.id.tvItemChargeStatus);
            tvDateCreated = (TextView) itemView.findViewById(R.id.tvItemChargeCreated);


            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());
            }
        }
    }


}
