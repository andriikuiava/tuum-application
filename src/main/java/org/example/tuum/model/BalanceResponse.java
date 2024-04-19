package org.example.tuum.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BalanceResponse {
    @JsonProperty("amount")
    private double amount;

    @JsonProperty("currency_code")
    private String currencyCode;

    public BalanceResponse(double amount, String currencyCode) {
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}