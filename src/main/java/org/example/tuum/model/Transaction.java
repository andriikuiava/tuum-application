package org.example.tuum.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {
    private int transactionId;

    @JsonProperty("account_id")
    private int accountId;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("direction")
    private String direction;

    @JsonProperty("description")
    private String description;

    @JsonProperty("balance_before")
    private double balanceBefore;

    public Transaction() {
    }

    public Transaction(int transactionId, int accountId, String currency, double amount, String direction, String description) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.currency = currency;
        this.amount = amount;
        this.direction = direction;
        this.description = description;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBalanceBefore() {
        return balanceBefore;
    }

    public void setBalanceBefore(double balanceBefore) {
        this.balanceBefore = balanceBefore;
    }
}
