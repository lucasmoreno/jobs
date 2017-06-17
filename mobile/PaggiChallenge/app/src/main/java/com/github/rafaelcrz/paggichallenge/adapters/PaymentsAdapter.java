package com.github.rafaelcrz.paggichallenge.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.rafaelcrz.paggichallenge.R;
import com.github.rafaelcrz.paggichallenge.api.models.Payment;
import com.github.rafaelcrz.paggichallenge.util.PaggiFormatterUtil;

import java.util.List;


public class PaymentsAdapter extends RecyclerView.Adapter<PaymentsAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<Payment> paymentList;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public PaymentsAdapter(Context c, List<Payment> paymentList) {
        this.mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.paymentList = paymentList;
        this.context = c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_payment, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int position) {

        Payment payment = paymentList.get(position);

        String compensated_at = PaggiFormatterUtil.formatStringDate(context, payment.getCompensated_at(), PaggiFormatterUtil.WITH_SECOND_PATTERN);
        String amount;
        try {
            amount = PaggiFormatterUtil.currencyFormatter(payment.getAmount());
        } catch (Exception e) {
            amount = String.valueOf(payment.getAmount());
            e.printStackTrace();
        }
        String status = payment.getStatus();

        if (status.equals("approved")) {
            myViewHolder.tvStatus.setBackgroundResource(R.color.green);
        } else if (status.equals("declined")) {
            myViewHolder.tvStatus.setBackgroundResource(R.color.red);
        }

        myViewHolder.tvCompensated.setText(compensated_at);
        myViewHolder.tvStatus.setText(status);
        myViewHolder.tvAmount.setText("$ " + amount);


    }

    @Override
    public int getItemCount() {
        try {
            return this.paymentList.size();
        } catch (Exception e) {
            return 0;
        }
    }


    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        mRecyclerViewOnClickListenerHack = r;
    }

    public void addItem(Payment payment, int position) {
        paymentList.add(payment);
        notifyItemInserted(position);
    }

    public void clear() {
        paymentList.clear();
        notifyDataSetChanged();
    }

    public List<Payment> getList() {
        return paymentList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvAmount, tvCompensated, tvStatus;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvAmount = (TextView) itemView.findViewById(R.id.tvItemPayAmount);
            tvCompensated = (TextView) itemView.findViewById(R.id.tvItemPayCompensated);
            tvStatus = (TextView) itemView.findViewById(R.id.tvItemPayStatus);
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
