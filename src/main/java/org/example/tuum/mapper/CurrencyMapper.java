package org.example.tuum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.tuum.model.Currency;

@Mapper
public interface CurrencyMapper {
    @Select("SELECT * FROM currency WHERE currencycode = #{currencyCode}")
    Currency getCurrencyByCode(String currencyCode);

    @Select("SELECT * FROM currency WHERE currencyid = #{currencyId}")
    Currency getCurrencyById(int currencyId);
}