package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankInterest {

    private String bankSecurityId;

    private BigDecimal accruedInterest;

    private Double avgLife;

    private BigDecimal principalValue;
}
