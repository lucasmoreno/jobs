package com.github.rafaelcrz.paggichallenge.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.github.rafaelcrz.android_endless_scroll_lib.ScrollEndless;
import com.github.rafaelcrz.paggichallenge.R;
import com.github.rafaelcrz.paggichallenge.util.FragmentUtil;
import com.github.rafaelcrz.paggichallenge.views.fragments.PaymentFragment;
import com.github.rafaelcrz.paggichallenge.views.fragments.TransactionFragment;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton actionButton;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        try {
            FragmentUtil.replaceFragment(getSupportFragmentManager(), R.id.content, new TransactionFragment());
        } catch (Exception e) {
            e.printStackTrace();
        }

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AmountValueActivity.class);
                startActivity(intent);
            }
        });


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_transaction:
                    try {
                        FragmentUtil.replaceFragment(getSupportFragmentManager(), R.id.content, new TransactionFragment());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                case R.id.navigation_payments:
                    try {
                        FragmentUtil.replaceFragment(getSupportFragmentManager(), R.id.content, new PaymentFragment());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
            }
            return false;
        }

    };

}
