package org.example.tuum.controller;

import org.example.tuum.mapper.*;
import org.example.tuum.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionMapper transactionMapper;
    private final CurrencyMapper currencyMapper;
    private final BalanceMapper balanceMapper;
    private final AccountMapper accountMapper;

    public TransactionController(TransactionMapper transactionMapper, CurrencyMapper currencyMapper, BalanceMapper balanceMapper, AccountMapper accountMapper) {
        this.transactionMapper = transactionMapper;
        this.currencyMapper = currencyMapper;
        this.balanceMapper = balanceMapper;
        this.accountMapper = accountMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {

        Account account = accountMapper.getAccountById(transaction.getAccountId());
        if (account == null) {
            return ResponseEntity.badRequest().body("Account not found");
        }

        Currency currency = currencyMapper.getCurrencyByCode(transaction.getCurrency());
        Balance balance = balanceMapper.findBalanceByCurrencyAndAccountId(currency.getCurrencyId(), transaction.getAccountId());

        if (accountMapper.getAccountById(transaction.getAccountId()) == null) {
            return ResponseEntity.badRequest().body("Account not found");
        }

        if (balance == null) {
            return ResponseEntity.badRequest().body("Balance with currency " + transaction.getCurrency() + " for account id " + transaction.getAccountId() + " not found");
        }

        if (balance.getCurrencyId() != currency.getCurrencyId()) {
            return ResponseEntity.badRequest().body("Currency mismatch");
        }

        if (balance.getAmount() < transaction.getAmount() && Objects.equals(transaction.getDirection(), "OUT")) {
            return ResponseEntity.badRequest().body("Insufficient balance");
        }

        if (transaction.getAmount() <= 0) {
            return ResponseEntity.badRequest().body("Invalid amount");
        }

        if (!Objects.equals(transaction.getDirection(), "OUT") && !Objects.equals(transaction.getDirection(), "IN")) {
            return ResponseEntity.badRequest().body("Invalid direction");
        }

        if (transaction.getDescription().isEmpty() || transaction.getDescription().length() > 255) {
            return ResponseEntity.badRequest().body("Description cannot be empty or more than 255 characters");
        }


        transaction.setBalanceBefore(balance.getAmount());
        transactionMapper.createTransaction(transaction);

        if (transaction.getDirection().equals("OUT")) {
            balance.setAmount(balance.getAmount() - transaction.getAmount());
            balanceMapper.updateBalance(balance);
        } else {
            balance.setAmount(balance.getAmount() + transaction.getAmount());
            balanceMapper.updateBalance(balance);
        }

        return ResponseEntity.ok().body(transaction);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransactionById(@PathVariable int transactionId) {
        Transaction transaction = transactionMapper.getTransactionById(transactionId);
        if (transaction == null) {
            return ResponseEntity.badRequest().body("Transaction with ID " + transactionId + " not found");
        } else {
            return ResponseEntity.ok().body(transaction);
        }
    }
}
