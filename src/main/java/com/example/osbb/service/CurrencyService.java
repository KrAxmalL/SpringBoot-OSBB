package com.example.osbb.service;

import java.math.BigDecimal;

public interface CurrencyService {

  BigDecimal getAmountInHrn(Integer currencyIsoCode, BigDecimal amount);
}
