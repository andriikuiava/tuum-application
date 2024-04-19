package org.example.tuum;

import org.example.tuum.controller.AccountController;
import org.example.tuum.mapper.AccountMapper;
import org.example.tuum.mapper.CurrencyMapper;
import org.example.tuum.model.Account;
import org.example.tuum.model.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private CurrencyMapper currencyMapper;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAccountById_AccountNotFound() {
        when(accountMapper.getAccountById(1)).thenReturn(null);

        ResponseEntity<?> response = accountController.getAccountById(1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Account with ID 1 not found", response.getBody());
    }

    @Test
    void testGetAccountById_AccountFound() {
        when(accountMapper.getAccountById(1)).thenReturn(new Account());
        when(accountMapper.getBalancesByAccountId(1)).thenReturn(new ArrayList<>());

        ResponseEntity<?> response = accountController.getAccountById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testCreateAccount_InvalidCurrency() {
        Account account = new Account();
        account.setCurrencies(new ArrayList<>());
        account.getCurrencies().add("INVALID");

        ResponseEntity<?> response = accountController.createAccount(account);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Please provide valid currencies", response.getBody());
    }

    @Test
    void testCreateAccount_ValidCurrency() {
        Account account = new Account();
        account.setCurrencies(new ArrayList<>());
        account.getCurrencies().add("EUR");

        when(currencyMapper.getCurrencyByCode("EUR")).thenReturn(new Currency());

        ResponseEntity<?> response = accountController.createAccount(account);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testCreateAccount_InValidCurrency() {
        Account account = new Account();
        account.setCurrencies(new ArrayList<>());
        account.getCurrencies().add("INVALID");

        ResponseEntity<?> response = accountController.createAccount(account);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Please provide valid currencies", response.getBody());
    }
}
