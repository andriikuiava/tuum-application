package org.example.tuum.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.example.tuum.model.Transaction;

@Mapper
public interface TransactionMapper {
    @Select("SELECT * FROM transaction WHERE transactionid = #{transactionId}")
    Transaction getTransactionById(int transactionId);

    @Insert("INSERT INTO transaction (accountid, amount, direction, description, currency, balancebefore) VALUES (#{accountId}, #{amount}, #{direction}, #{description}, #{currency}, #{balanceBefore})")
    @Options(useGeneratedKeys = true, keyProperty = "transactionId")
    void createTransaction(Transaction transaction);
}
