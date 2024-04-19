package org.example.tuum.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.tuum.model.Transaction;

import java.util.List;

public class TransactionResponse {
    @JsonProperty("transaction_id")
    private int transactionId;

    @JsonProperty("account_id")
    private int accountId;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("direction")
    private String direction;

    @JsonProperty("balance_before")
    private double balanceBefore;

    @JsonProperty("description")
    private String description;
}
