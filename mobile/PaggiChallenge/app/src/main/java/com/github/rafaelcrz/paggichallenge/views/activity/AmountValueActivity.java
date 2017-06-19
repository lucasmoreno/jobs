package com.github.rafaelcrz.paggichallenge.views.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.github.rafaelcrz.paggichallenge.R;
import com.github.rafaelcrz.paggichallenge.util.PaggiFormatterUtil;
import com.github.rafaelcrz.paggichallenge.util.PaggiUtil;


public class AmountValueActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btOk, btCancel;
    private ImageButton btDel;
    private EditText edAmount;

    private StringBuilder amount;
    private Context context;
    private android.content.IntentFilter intentFilter;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_value);

        context = AmountValueActivity.this;
        amount = new StringBuilder();

        loadView();

        intentFilter = new IntentFilter();
        intentFilter.addAction("CLOSE");
    }

    private void loadView() {
        edAmount = (EditText) findViewById(R.id.edSaleAmount);
        bt1 = (Button) findViewById(R.id.button1);
        btDel = (ImageButton) findViewById(R.id.buttonDel);
        btOk = (Button) findViewById(R.id.btSaleCancel);
        btCancel = (Button) findViewById(R.id.btSaleOk);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);
        bt4 = (Button) findViewById(R.id.button4);
        bt5 = (Button) findViewById(R.id.button5);
        bt6 = (Button) findViewById(R.id.button6);
        bt7 = (Button) findViewById(R.id.button7);
        bt8 = (Button) findViewById(R.id.button8);
        bt9 = (Button) findViewById(R.id.button9);
        bt0 = (Button) findViewById(R.id.button0);
        btCancel.setOnClickListener(this);
        btOk.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        bt0.setOnClickListener(this);
        btDel.setOnClickListener(this);
    }

    private void showValue(String value) {
        amount.append(value);
        String amountFormatted = null;
        try {
            amountFormatted = PaggiFormatterUtil.currencyFormatter(Integer.parseInt(amount.toString()));
        } catch (Exception e) {
            amountFormatted = amount.toString();
            e.printStackTrace();
        }
        edAmount.setText(amountFormatted);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button0:
                showValue("0");
                break;
            case R.id.button1:
                showValue("1");
                break;
            case R.id.button2:
                showValue("2");
                break;
            case R.id.button3:
                showValue("3");
                break;
            case R.id.button4:
                showValue("4");
                break;
            case R.id.button5:
                showValue("5");
                break;
            case R.id.button6:
                showValue("6");
                break;
            case R.id.button7:
                showValue("7");
                break;
            case R.id.button8:
                showValue("8");
                break;
            case R.id.button9:
                showValue("9");
                break;
            case R.id.btSaleCancel:
                finish();
                break;
            case R.id.btSaleOk:
                if (!amount.toString().equals("")) {
                    Intent intent = new Intent(context, ConfirmTransactionActivity.class);
                    intent.putExtra(PaggiUtil.AMOUNT_VALUE, amount.toString());
                    startActivity(intent);
                } else {
                    edAmount.setError("Please, put the amount value.");
                }
                break;
            case R.id.buttonDel:
                int length = amount.length();
                if (length > 0) {
                    amount.deleteCharAt(length - 1);
                    edAmount.setText(amount.toString());
                }
                break;

        }
    }

    /**
     * Esse Broadcast é para fechar essa activity quando ele disparado pela ultima activity
     * de confirmação de transação.
     * Porque o usuario por querer voltar as telas até esta.
     */
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
