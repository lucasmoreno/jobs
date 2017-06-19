package com.github.rafaelcrz.paggichallenge.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Rafael Felipe on 13/06/2017.
 */

public class Transaction implements Serializable {

    private String status;
    private String statement_descriptor;
    private boolean risk_analysis;
    private String receipt_email;
    private String id;
    private String acquirer_message;
    private String acquirer_code;
    private String expected_compensation;
    private String destination;
    private String description;
    private String customer;
    private String created_at;
    private boolean capture;
    private int installments_number;
    private int amount;

    public Transaction() {}

    public String getStatus() {
        return status;
    }

    public String getStatement_descriptor() {
        return statement_descriptor;
    }

    public boolean isRisk_analysis() {
        return risk_analysis;
    }

    public String getReceipt_email() {
        return receipt_email;
    }

    public String getId() {
        return id;
    }

    public String getAcquirer_message() {
        return acquirer_message;
    }

    public String getAcquirer_code() {
        return acquirer_code;
    }

    public String getExpected_compensation() {
        return expected_compensation;
    }

    public String getDestination() {
        return destination;
    }

    public String getDescription() {
        return description;
    }

    public String getCustomer() {
        return customer;
    }

    public String getCreated_at() {
        return created_at;
    }

    public boolean isCapture() {
        return capture;
    }

    public int getInstallments_number() {
        return installments_number;
    }

    public int getAmount() {
        return amount;
    }
}
