package com.github.rafaelcrz.paggichallenge.views.fragments;

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

import com.github.rafaelcrz.android_endless_scroll_lib.EndlessListener;
import com.github.rafaelcrz.android_endless_scroll_lib.ScrollEndless;
import com.github.rafaelcrz.paggichallenge.R;
import com.github.rafaelcrz.paggichallenge.adapters.TransactionAdapter;
import com.github.rafaelcrz.paggichallenge.api.interfaces.PaggiListener;
import com.github.rafaelcrz.paggichallenge.api.models.Transaction;
import com.github.rafaelcrz.paggichallenge.api.models.TransactionCatalog;
import com.github.rafaelcrz.paggichallenge.api.requestsync.TransactionApi;
import com.github.rafaelcrz.paggichallenge.util.PaggiUtil;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Rafael Felipe on 14/06/2017.
 */

public class TransactionFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private Context mContext;

    private ScrollEndless scrollEndless;
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private TransactionAdapter paymentsAdapter;

    private TransactionApi paymentApi;
    private int PAGE_SIZE = 10;
    private int page = 1;
    private LinearLayout layout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this.getActivity();
        paymentApi = new TransactionApi(mContext);
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

        paymentsAdapter = new TransactionAdapter(this.getActivity(), new ArrayList<Transaction>());
        recyclerView.setAdapter(paymentsAdapter);

        scrollEndless = new ScrollEndless(mContext, recyclerView, layoutManager);
        scrollEndless.setTotalPage(PAGE_SIZE);

        paymentApi.setPage("1");
        paymentApi.setPageSize(String.valueOf(PAGE_SIZE));
        getTransactions();

        scrollEndless.addScrollEndless(new EndlessListener() {
            @Override
            public void onLoadMore() {
                getTransactions();
            }

            @Override
            public void onLoadAllFinish() {
                Snackbar snackbar = Snackbar.make(layout, "Esses são todas as suas transações.", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                snackbar.show();
            }
        });

    }

    private void getTransactions() {
        scrollEndless.isLoading(true);
        swipeRefresh.setRefreshing(true);
        paymentApi.getTransaction(new PaggiListener<TransactionCatalog>() {
            @Override
            public void onResponde(Call call, Response response) {
                if (response.isSuccessful()) {
                    TransactionCatalog catalog = (TransactionCatalog) response.body();
                    for (Transaction c : catalog.getResult()) {
                        paymentsAdapter.addItem(c, paymentsAdapter.getItemCount() - 1);
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
        getTransactions();
    }
}
