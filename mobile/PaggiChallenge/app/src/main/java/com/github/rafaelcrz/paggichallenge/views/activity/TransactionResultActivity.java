package com.github.rafaelcrz.paggichallenge.views.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.rafaelcrz.paggichallenge.R;
import com.github.rafaelcrz.paggichallenge.api.models.TransactionResult;
import com.github.rafaelcrz.paggichallenge.util.PaggiFormatterUtil;
import com.github.rafaelcrz.paggichallenge.util.PaggiUtil;

public class TransactionResultActivity extends AppCompatActivity {

    private TextView edAmount, edDescriptor;
    private Button btFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_result);

        edAmount = (TextView) findViewById(R.id.tvTransactResultAmount);
        edDescriptor = (TextView) findViewById(R.id.tvTransactResultDescriptor);
        btFinish = (Button) findViewById(R.id.btTransactResultReturn);

        Intent intent = getIntent();
        if (intent != null) {
            TransactionResult transactionResult = (TransactionResult) intent.getExtras().getSerializable(PaggiUtil.TRANSACTION_RESULT);
            if (transactionResult != null) {
                String amount = null;
                try {
                    amount = PaggiFormatterUtil.currencyFormatter(transactionResult.getAmount());
                } catch (Exception e) {
                    amount = String.valueOf(transactionResult.getAmount());
                    e.printStackTrace();
                }
                edAmount.setText("$ " + amount);
                edDescriptor.setText(transactionResult.getStatement_descriptor());
            }
        }


        btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentClose = new Intent("CLOSE");
                sendBroadcast(intentClose);
                finish();
            }
        });
    }

}
