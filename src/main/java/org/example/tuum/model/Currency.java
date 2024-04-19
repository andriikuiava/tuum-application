package org.example.tuum.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Currency {
    @JsonProperty("currency_id")
    private int currencyId;

    @JsonProperty("currency_code")
    private String currencyCode;

    public int getCurrencyId() {
        return currencyId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}