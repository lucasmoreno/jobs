package com.github.rafaelcrz.paggichallenge.api.models;

import java.io.Serializable;

/**
 * Created by Rafael Felipe on 16/06/2017.
 */

public class Installment implements Serializable {

    private String status;
    private int number;
    private String expected_date;
    private int amount;

    public Installment(){}

    public String getStatus() {
        return status;
    }

    public int getNumber() {
        return number;
    }

    public String getExpected_date() {
        return expected_date;
    }

    public int getAmount() {
        return amount;
    }

}
