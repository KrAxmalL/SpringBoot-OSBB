package com.example.osbb.service;

import com.example.osbb.domain.dto.currency.MonobankCurrencyReponse;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

  private final WebClient monobankWebClient;

  private static final Integer UAH_ISO_CODE = 980;

  @Override
  public BigDecimal getAmountInHrn(Integer currencyIsoCode, BigDecimal amount) {
    final List<MonobankCurrencyReponse> rates =
        monobankWebClient
            .get()
            .retrieve()
            .bodyToFlux(MonobankCurrencyReponse.class)
            .collectList()
            .block();

    return rates.stream()
        .filter(
            monobankCurrencyResponse ->
                monobankCurrencyResponse.getCurrencyCodeA().equals(currencyIsoCode)
                    && monobankCurrencyResponse.getCurrencyCodeB().equals(UAH_ISO_CODE))
        .map(MonobankCurrencyReponse::getRateSell)
        .map(rateSell -> rateSell.multiply(amount))
        .findFirst()
        .get();
  }
}
