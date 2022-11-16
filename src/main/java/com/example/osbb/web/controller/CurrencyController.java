package com.example.osbb.web.controller;

import com.example.osbb.domain.dto.currency.CurrencyDTO;
import com.example.osbb.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "Currencies",
    description = "Converting money amounts between currencies using Monobank API")
@RestController
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class CurrencyController {

  private final CurrencyService currencyService;

  @Operation(
      summary = "Get amount in HRN to pay. Should provide valid ISO currency code and amount")
  @GetMapping
  public CurrencyDTO getAmountInHrn(
      @RequestParam String currencyIsoCode, @RequestParam BigDecimal amount) {
    final BigDecimal amountInHrn =
        currencyService.getAmountInHrn(Integer.parseInt(currencyIsoCode), amount);
    return CurrencyDTO.builder().amountInHrn(amountInHrn).build();
  }
}
