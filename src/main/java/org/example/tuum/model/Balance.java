package org.example.tuum.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Balance {
    @JsonProperty("balance_id")
    private int balanceId;

    @JsonProperty("available_amount")
    private double amount;

    @JsonProperty("currency_id")
    private int currencyId;

    @JsonProperty("account_id")
    private int accountId;

    public int getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(int balanceId) {
        this.balanceId = balanceId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

}

