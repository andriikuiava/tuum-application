package org.example.tuum.controller;

import org.example.tuum.mapper.AccountMapper;
import org.example.tuum.mapper.CurrencyMapper;
import org.example.tuum.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountMapper accountMapper;
    private final CurrencyMapper currencyMapper;

    @Autowired
    public AccountController(AccountMapper accountMapper, CurrencyMapper currencyMapper) {
        this.accountMapper = accountMapper;
        this.currencyMapper = currencyMapper;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable int accountId) {
        Account account = accountMapper.getAccountById(accountId);
        List<Balance> balances = accountMapper.getBalancesByAccountId(accountId);
        List<BalanceResponse> balanceResponses = createBalanceResponses(balances);

        if (account == null) {
            return ResponseEntity.badRequest().body("Account with ID " + accountId + " not found");
        } else {
            return ResponseEntity.ok().body(createAccountResponse(account, balanceResponses));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        List<String> allowedCurrencies = Arrays.asList("EUR", "SEK", "GBP", "USD");
        List<String> currencies = account.getCurrencies();

        for (String currencyCode : currencies) {
            if (!allowedCurrencies.contains(currencyCode)) {
                return ResponseEntity.badRequest().body("Please provide valid currencies");
            }
        }

        if (account.getCountry() == null) {
            return ResponseEntity.badRequest().body("Please provide a country");
        }

        accountMapper.createAccount(account);
        int accountId = account.getAccountId();

        for (String currencyCode : currencies) {
            Currency currency = currencyMapper.getCurrencyByCode(currencyCode);
            if (currency != null) {
                Balance balance = new Balance();
                balance.setAccountId(accountId);
                balance.setCurrencyId(currency.getCurrencyId());
                balance.setAmount(0.0);
                accountMapper.createBalance(balance);
            }
        }

        List<BalanceResponse> balanceResponses = new ArrayList<>();
        for (String currencyCode : currencies) {
            BalanceResponse balanceResponse = new BalanceResponse(0.0, currencyCode);
            balanceResponses.add(balanceResponse);
        }

        AccountResponse accountResponse = createAccountResponse(account, balanceResponses);

        return ResponseEntity.ok().body(accountResponse);
    }

    private List<BalanceResponse> createBalanceResponses(List<Balance> balances) {
        List<BalanceResponse> balanceResponses = new ArrayList<>();
        for (Balance balance : balances) {
            Currency currency = currencyMapper.getCurrencyById(balance.getCurrencyId());
            BalanceResponse balanceResponse = new BalanceResponse(balance.getAmount(), currency.getCurrencyCode());
            balanceResponses.add(balanceResponse);
        }
        return balanceResponses;
    }

    private AccountResponse createAccountResponse(Account account, List<BalanceResponse> balanceResponses) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountId(account.getAccountId());
        accountResponse.setCustomerId(account.getCustomerId());
        accountResponse.setBalances(balanceResponses);
        return accountResponse;
    }
}
