package com.tw.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TransactionRepositoryTest {
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void init() {
        Clock clock = mock(Clock.class);
        transactionRepository = new TransactionRepository(clock);
    }

    @Test
    public void should_have_amount_when_addDeposit_given_amount() {
        //given
        int amount = 500;
        transactionRepository.addDeposit(amount);

        //when
        List<Transaction> transactions = transactionRepository.allTransactions();

        //then
        assertEquals(1, transactions.size());
        assertEquals(500, transactions.get(0).amount());
    }

    @Test
    public void should_have_negative_amount_when_addWithdraw_given_amount() {
        //given
        int amount = 500;
        transactionRepository.addWithdraw(amount);

        //when
        List<Transaction> transactions = transactionRepository.allTransactions();

        //then
        assertEquals(1, transactions.size());
        assertEquals(-500, transactions.get(0).amount());
    }

}
