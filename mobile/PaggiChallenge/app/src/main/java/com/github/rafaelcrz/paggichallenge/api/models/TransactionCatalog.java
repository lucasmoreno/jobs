package com.github.rafaelcrz.paggichallenge.api.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rafael Felipe on 13/06/2017.
 */

public class TransactionCatalog implements Serializable {

    private int total;
    private List<Transaction> result;

    public TransactionCatalog() {
    }

    public TransactionCatalog(int total, List<Transaction> result) {
        this.total = total;
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public List<Transaction> getResult() {
        return result;
    }
}


