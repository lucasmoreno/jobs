package com.github.rafaelcrz.paggichallenge.views.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.rafaelcrz.android_endless_scroll_lib.ScrollEndless;
import com.github.rafaelcrz.paggichallenge.R;
import com.github.rafaelcrz.paggichallenge.adapters.PaymentsAdapter;
import com.github.rafaelcrz.paggichallenge.adapters.RecyclerViewOnClickListenerHack;
import com.github.rafaelcrz.paggichallenge.api.interfaces.PaggiListener;
import com.github.rafaelcrz.paggichallenge.api.models.Payment;
import com.github.rafaelcrz.paggichallenge.api.models.PaymentCatalog;
import com.github.rafaelcrz.paggichallenge.api.requestsync.PaymentApi;
import com.github.rafaelcrz.paggichallenge.util.PaggiUtil;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Rafael Felipe on 14/06/2017.
 */

public class PaymentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private Context mContext;

    private ScrollEndless scrollEndless;
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private PaymentsAdapter paymentsAdapter;

    private PaymentApi paymentApi;

    private int PAGE_SIZE = 10;
    private int page = 1;
    private LinearLayout layout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this.getActivity();
        paymentApi = new PaymentApi(mContext);
        layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layout = (LinearLayout) view.findViewById(R.id.containerPaymet);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        paymentsAdapter = new PaymentsAdapter(this.getActivity(), new ArrayList<Payment>());
        recyclerView.setAdapter(paymentsAdapter);

        scrollEndless = new ScrollEndless(mContext, recyclerView, layoutManager);
        scrollEndless.setTotalPage(PAGE_SIZE);

        paymentApi.setPage("1");
        paymentApi.setPageSize(String.valueOf(PAGE_SIZE));

        getPayments();

        scrollEndless.addScrollEndless(new ScrollEndless.EndlessScrollListener() {
            @Override
            public void onLoadMore() {
                getPayments();
            }

            @Override
            public void onLoadAllFinish() {
                PaggiUtil.snackBarMessage(layout, "Essas são todas as suas transações.");
            }
        });

        paymentsAdapter.setRecyclerViewOnClickListenerHack(new RecyclerViewOnClickListenerHack() {
            @Override
            public void onClickListener(View view, int position) {
                Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getPayments() {
        scrollEndless.isLoading(true);
        swipeRefresh.setRefreshing(true);
        paymentApi.getPayments(new PaggiListener<PaymentCatalog>() {
            @Override
            public void onResponde(Call call, Response response) {
                if (response.isSuccessful()) {
                    PaymentCatalog catalog = (PaymentCatalog) response.body();
                    for (Payment p : catalog.getResult()) {
                        paymentsAdapter.addItem(p, paymentsAdapter.getItemCount() - 1);
                    }
                    swipeRefresh.setRefreshing(false);
                    scrollEndless.isLoading(false);
                    scrollEndless.setPage(page);
                    page = scrollEndless.getPage() + 1;
                } else {
                    PaggiUtil.snackBarMessage(layout, response.message());
                }
            }

            @Override
            public void onFailure(Call call, Throwable throwable) {
                swipeRefresh.setRefreshing(false);
                scrollEndless.isLoading(false);
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void onRefresh() {
        paymentsAdapter.clear();
        page = 1;
        getPayments();
    }
}
