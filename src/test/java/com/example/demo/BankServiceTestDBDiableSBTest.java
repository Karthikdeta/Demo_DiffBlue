package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BankService.class})
@ExtendWith(SpringExtension.class)
class BankServiceTestDBDiableSBTest {
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
}

