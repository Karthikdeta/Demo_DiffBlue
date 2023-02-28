package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BankService.class})
@ExtendWith(SpringExtension.class)
class BankServiceTest {
    @MockBean
    private BankRepository bankRepository;

    @Autowired
    private BankService bankService;

    /**
     * Method under test: {@link BankService#insertBankAccounts()}
     */
    @Test
    void testInsertBankAccounts() {
        when(bankRepository.saveAll((Iterable<BankDetails>) any())).thenReturn(new ArrayList<>());
        bankService.insertBankAccounts();
        verify(bankRepository, atLeast(1)).saveAll((Iterable<BankDetails>) any());
    }

    /**
     * Method under test: {@link BankService#getBank(int, String)}
     */
    @Test
    void testGetBank() {
        BankDetails bankDetails = new BankDetails(123, "Bank Name", "GB", "MD", 1L);

        when(bankRepository.findByBankIdAndBankNameIgnoreCase(anyInt(), (String) any())).thenReturn(bankDetails);
        assertSame(bankDetails, bankService.getBank(123, "Bank Name"));
        verify(bankRepository).findByBankIdAndBankNameIgnoreCase(anyInt(), (String) any());
    }

    /**
     * Method under test: {@link BankService#calculateBankInterest(BankInterest)}
     */
    @Test
    void testCalculateBankInterest() {
        when(bankRepository.findByBankNameIgnoreCase((String) any()))
                .thenReturn(new BankDetails(123, "Bank Name", "GB", "MD", 1L));
        assertNull(bankService.calculateBankInterest(new BankInterest()));
        verify(bankRepository).findByBankNameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link BankService#calculateBankInterest(BankInterest)}
     */
    @Test
    void testCalculateBankInterest2() {
        when(bankRepository.findByBankNameIgnoreCase((String) any())).thenReturn(null);
        assertNull(bankService.calculateBankInterest(new BankInterest()));
        verify(bankRepository).findByBankNameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link BankService#calculateBankInterest(BankInterest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCalculateBankInterest3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.demo.BankInterest.getBankSecurityId()" because "bankInterest" is null
        //       at com.example.demo.BankService.calculateBankInterest(BankService.java:39)
        //   See https://diff.blue/R013 to resolve this issue.

        when(bankRepository.findByBankNameIgnoreCase((String) any()))
                .thenReturn(new BankDetails(123, "Bank Name", "GB", "MD", 1L));
        bankService.calculateBankInterest(null);
    }

    /**
     * Method under test: {@link BankService#calculateBankInterest(BankInterest)}
     */
    @Test
    void testCalculateBankInterest4() {
        when(bankRepository.findByBankNameIgnoreCase((String) any()))
                .thenReturn(new BankDetails(123, "Bank Name", "GB", "MD", 1L));
        BigDecimal accruedInterest = BigDecimal.valueOf(42L);
        assertEquals("176.400000000000",
                bankService.calculateBankInterest(new BankInterest("42", accruedInterest, 10.0d, BigDecimal.valueOf(42L)))
                        .toString());
        verify(bankRepository).findByBankNameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link BankService#calculateBankInterest(BankInterest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCalculateBankInterest5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NumberFormatException: Character N is neither a decimal digit number, decimal point, nor "e" notation exponential mark.
        //       at java.math.BigDecimal.<init>(BigDecimal.java:582)
        //       at java.math.BigDecimal.<init>(BigDecimal.java:467)
        //       at java.math.BigDecimal.<init>(BigDecimal.java:896)
        //       at java.math.BigDecimal.valueOf(BigDecimal.java:1354)
        //       at com.example.demo.BankService.calculateBankInterest(BankService.java:42)
        //   See https://diff.blue/R013 to resolve this issue.

        when(bankRepository.findByBankNameIgnoreCase((String) any()))
                .thenReturn(new BankDetails(123, "Bank Name", "GB", "MD", 1L));
        BankInterest bankInterest = mock(BankInterest.class);
        when(bankInterest.getAvgLife()).thenReturn(Double.NaN);
        when(bankInterest.getPrincipalValue()).thenReturn(BigDecimal.valueOf(42L));
        when(bankInterest.getBankSecurityId()).thenReturn("42");
        when(bankInterest.getAccruedInterest()).thenReturn(BigDecimal.valueOf(42L));
        bankService.calculateBankInterest(bankInterest);
    }

    /**
     * Method under test: {@link BankService#calculateBankInterest(BankInterest)}
     */
    @Test
    void testCalculateBankInterest6() {
        when(bankRepository.findByBankNameIgnoreCase((String) any()))
                .thenReturn(new BankDetails(123, "Bank Name", "GB", "MD", 1L));
        BankInterest bankInterest = mock(BankInterest.class);
        when(bankInterest.getAvgLife()).thenReturn(10.0d);
        when(bankInterest.getPrincipalValue()).thenReturn(null);
        when(bankInterest.getBankSecurityId()).thenReturn("42");
        when(bankInterest.getAccruedInterest()).thenReturn(BigDecimal.valueOf(42L));
        assertNull(bankService.calculateBankInterest(bankInterest));
        verify(bankRepository).findByBankNameIgnoreCase((String) any());
        verify(bankInterest).getAvgLife();
        verify(bankInterest).getBankSecurityId();
        verify(bankInterest).getAccruedInterest();
        verify(bankInterest).getPrincipalValue();
    }

    /**
     * Method under test: {@link BankService#calculateBankInterest(BankInterest)}
     */
    @Test
    void testCalculateBankInterest7() {
        when(bankRepository.findByBankNameIgnoreCase((String) any()))
                .thenReturn(new BankDetails(123, "Bank Name", "GB", "MD", 1L));
        BankInterest bankInterest = mock(BankInterest.class);
        when(bankInterest.getAvgLife()).thenReturn(null);
        when(bankInterest.getPrincipalValue()).thenReturn(null);
        when(bankInterest.getBankSecurityId()).thenReturn("42");
        when(bankInterest.getAccruedInterest()).thenReturn(BigDecimal.valueOf(42L));
        assertNull(bankService.calculateBankInterest(bankInterest));
        verify(bankRepository).findByBankNameIgnoreCase((String) any());
        verify(bankInterest).getAvgLife();
        verify(bankInterest).getBankSecurityId();
        verify(bankInterest).getAccruedInterest();
    }
}

