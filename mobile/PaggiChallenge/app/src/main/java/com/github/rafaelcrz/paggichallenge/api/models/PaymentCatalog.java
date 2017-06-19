package com.github.rafaelcrz.paggichallenge.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rafael Felipe on 13/06/2017.
 */

public class PaymentCatalog implements Serializable{

    private int total;
    private List<Payment> result;

    public PaymentCatalog() {}

    public PaymentCatalog(int total, List<Payment> result) {
        this.total = total;
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public List<Payment> getResult() {
        return result;
    }
}
