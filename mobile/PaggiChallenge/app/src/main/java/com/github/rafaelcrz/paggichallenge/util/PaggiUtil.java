package com.github.rafaelcrz.paggichallenge.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Rafael Felipe on 16/06/2017.
 */

public class PaggiUtil {

    public static final String AMOUNT_VALUE = "amountValue";

    public static final String TRANSACTION_RESULT = "transactionResult";

    /**
     * Snackbar usado para exibir mensagens simples, sem nenhuma ação no onlick. SOmente informativo
     * @param view View principa.
     * @param message Mensagem
     */
    public static void snackBarMessage(View view, String message) {
        Snackbar snackbarError = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
        snackbarError.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        snackbarError.show();
    }
}

