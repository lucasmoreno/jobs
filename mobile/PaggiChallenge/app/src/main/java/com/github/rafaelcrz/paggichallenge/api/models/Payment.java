package com.github.rafaelcrz.paggichallenge.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Rafael Felipe on 13/06/2017.
 */

public class Payment implements Serializable {

    private String status;
    private int amount_raw;
    private int amount;
    private String compensated_at;
    private String expected_compensation;
    private String destination;


    public Payment() {}

    public String getStatus() {
        return status;
    }

    public int getAmount_raw() {
        return amount_raw;
    }

    public int getAmount() {
        return amount;
    }

    public String getCompensated_at() {
        return compensated_at;
    }

    public String getExpected_compensation() {
        return expected_compensation;
    }

    public String getDestination() {
        return destination;
    }
}
