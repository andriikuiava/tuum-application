package org.example.tuum.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AccountResponse {
    @JsonProperty("account_id")
    private int accountId;

    @JsonProperty("customer_id")
    private int customerId;

    @JsonProperty("balances")
    private List<BalanceResponse> balances;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<BalanceResponse> getBalances() {
        return balances;
    }

    public void setBalances(List<BalanceResponse> balances) {
        this.balances = balances;
    }
}
