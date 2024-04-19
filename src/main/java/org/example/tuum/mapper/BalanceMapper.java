package org.example.tuum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.tuum.model.Balance;

@Mapper
public interface BalanceMapper {
    @Select("SELECT * FROM balance WHERE balanceid = #{balanceId} AND currencyid = #{currencyId}")
    Balance getBalanceById(int balanceId, int currencyId);


    @Select("SELECT * FROM balance WHERE accountid = #{accountId} AND currencyid = #{currencyId}")
    Balance findBalanceByCurrencyAndAccountId(int currencyId, int accountId);

    @Update("UPDATE balance SET amount = #{amount} WHERE balanceid = #{balanceId}")
    void updateBalance(Balance balance);
}
