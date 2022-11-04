package com.example.osbb.web.controller;

import com.example.osbb.domain.dto.currency.CurrencyDTO;
import com.example.osbb.service.CurrencyService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class CurrencyController {

  private final CurrencyService currencyService;

  @GetMapping
  public CurrencyDTO getAmountInHrn(
      @RequestParam String currencyIsoCode, @RequestParam BigDecimal amount) {
    final BigDecimal amountInHrn =
        currencyService.getAmountInHrn(Integer.parseInt(currencyIsoCode), amount);
    return CurrencyDTO.builder().amountInHrn(amountInHrn).build();
  }
}
