package com.example.osbb.domain.dto.currency;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonobankCurrencyReponse {

  private Integer currencyCodeA;
  private Integer currencyCodeB;
  private BigDecimal rateSell;
}
