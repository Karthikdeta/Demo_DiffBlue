package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Bank Service Test")
class BankServiceTestMachinet {

    @Autowired
    private BankService bankService;

    @Autowired
    private BankRepository bankRepository;

    @BeforeEach
    void setUp() {
        bankRepository.deleteAll();
    }

    @Test
    @DisplayName("Should return null when the bankid is invalid")
    void getBankWhenBankIdIsInvalid() {
        BankDetails bankDetails = bankService.getBank(1, "Axis Bank");
        assertNull(bankDetails);
    }

    @Test
    @DisplayName("Should return null when the bankname is invalid")
    void getBankWhenBankNameIsInvalid() {
        bankService.insertBankAccounts();
        BankDetails bankDetails = bankService.getBank(1, "Invalid Bank");
        assertNull(bankDetails);
    }

    @Test
    @DisplayName("Should return the bank when the bankid and bankname are valid")
    void getBankWhenBankIdAndBankNameAreValid() {
        bankService.insertBankAccounts();
        BankDetails bankDetails = bankService.getBank(1, "Axis Bank");
        assertNotNull(bankDetails);
        assertEquals(1, bankDetails.getBankId());
        assertEquals("Axis Bank", bankDetails.getBankName());
    }

    @Test
    @DisplayName("Should insert 5 bank accounts for each bank")
    void insertBankAccountsShouldInsert5BankAccountsForEachBank() {
        bankService.insertBankAccounts();
        List<BankDetails> bankDetailsList = bankRepository.findAll();
        assertEquals(25, bankDetailsList.size());
    }
}