package com.github.rafaelcrz.paggichallenge.views.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.rafaelcrz.paggichallenge.R;
import com.github.rafaelcrz.paggichallenge.api.interfaces.PaggiListener;
import com.github.rafaelcrz.paggichallenge.api.models.TransactionResult;
import com.github.rafaelcrz.paggichallenge.api.requestsync.TransactionApi;
import com.github.rafaelcrz.paggichallenge.util.PaggiUtil;

import retrofit2.Call;
import retrofit2.Response;


public class ConfirmTransactionActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText edAmount, edCardId, edCustomerChargedId, edInstallmentNumber;
    private Button btOk, btCancel;
    private LinearLayout linearLayout;

    private AlertDialog.Builder alertConfirmation;
    private ProgressDialog progressDialog;

    private TransactionApi transactionApi;
    private String mAmountValue;
    private IntentFilter intentFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_transaction);


        intentFilter = new IntentFilter();
        intentFilter.addAction("CLOSE");

        loadViews();

        transactionApi = new TransactionApi(this);

        Intent intent = getIntent();
        mAmountValue = intent.getStringExtra(PaggiUtil.AMOUNT_VALUE);
        if (mAmountValue != null) {
            edAmount.setText("$ " + mAmountValue);
        }

    }

    private void loadViews() {
        linearLayout = (LinearLayout) findViewById(R.id.linearConfirmTransaction);
        edAmount = (EditText) findViewById(R.id.edTransactAmount);
        edInstallmentNumber = (EditText) findViewById(R.id.edTransactInstallmentsNumber);
        edCustomerChargedId = (EditText) findViewById(R.id.edTransactCustomerId);
        edCardId = (EditText) findViewById(R.id.edTransactCard);
        btOk = (Button) findViewById(R.id.btConfirmSaleAmountOk);
        btCancel = (Button) findViewById(R.id.btConfirmSaleAmountCancel);
        btOk.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    private void showAlertDialogConfirmationPayment() {
        alertConfirmation = new AlertDialog.Builder(this);
        alertConfirmation.setTitle(getString(R.string.confirm_transaction));
        alertConfirmation.setMessage(getString(R.string.cofirm_transaction_msg));
        alertConfirmation.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {

                if (!edInstallmentNumber.getText().toString().equals("") || !edCustomerChargedId.getText().toString().equals("") || !edCardId.getText().toString().equals("")) {
                    String amount = mAmountValue;
                    String customerId = edCustomerChargedId.getText().toString();
                    String cardId = edCardId.getText().toString();
                    String installmentNumber = edInstallmentNumber.getText().toString();

                    setNewTransaction(amount, customerId, cardId, installmentNumber);
                } else {
                    Toast.makeText(ConfirmTransactionActivity.this, "Complete os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertConfirmation.show();
    }

    private void showProgressConfirm() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Confirming sale...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btConfirmSaleAmountOk:
                showAlertDialogConfirmationPayment();
                break;
            case R.id.btConfirmSaleAmountCancel:
                finish();
                break;
        }
    }

    private void setNewTransaction(String amount, String customerId, String cardId, String installmentNumber) {
        showProgressConfirm();

        transactionApi.setAmount(amount);
        transactionApi.setCustomerId(customerId);
        transactionApi.setCardId(cardId);
        transactionApi.setInstallmentNumber(installmentNumber);

        transactionApi.newTransaction(new PaggiListener<TransactionResult>() {
            @Override
            public void onResponde(Call<TransactionResult> call, Response<TransactionResult> response) {
                if (response.isSuccessful()) {
                    TransactionResult transactionResult = (TransactionResult) response.body();
                    Intent intent = new Intent(ConfirmTransactionActivity.this, TransactionResultActivity.class);
                    intent.putExtra(PaggiUtil.TRANSACTION_RESULT, transactionResult);
                    startActivity(intent);
                } else {
                    PaggiUtil.snackBarMessage(linearLayout, response.message());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<TransactionResult> call, Throwable throwable) {
                throwable.printStackTrace();
                if (progressDialog != null)
                    progressDialog.dismiss();
            }
        });
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if (intent.getAction().equals("CLOSE")) {
                    finish();
                }
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
