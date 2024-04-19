package org.example.tuum.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.example.tuum.model.Account;
import org.example.tuum.model.Balance;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("SELECT * FROM account WHERE accountid = #{accountId}")
    Account getAccountById(int accountId);

    @Insert("INSERT INTO account (customerid, country) VALUES (#{customerId}, #{country})")
    @Options(useGeneratedKeys = true, keyProperty = "accountId")
    void createAccount(Account account);

    @Insert("INSERT INTO balance (accountid, currencyid, amount) VALUES (#{accountId}, #{currencyId}, #{amount})")
    void createBalance(Balance balance);

    @Select("SELECT * FROM balance WHERE accountid = #{accountId}")
    List<Balance> getBalancesByAccountId(int accountId);

}