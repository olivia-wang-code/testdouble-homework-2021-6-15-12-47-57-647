package com.tw.banking;


import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AccountTest {
    @Test
    public void should_execute_addDeposit_of_transactionRepository_when_account_deposit() {
        //given
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        Printer printer = mock(Printer.class);

        //when
        Account account = new Account(transactionRepository, printer);
        int amount = 999;
        account.deposit(amount);

        //then
        verify(transactionRepository, times(1)).addDeposit(amount);
    }

    @Test
    public void should_execute_addWithdraw_of_transactionRepository_when_account_withdraw() {
        //given
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        Printer printer = mock(Printer.class);

        //when
        Account account = new Account(transactionRepository, printer);
        int amount = 999;
        account.withdraw(amount);

        //then
        verify(transactionRepository, times(1)).addWithdraw(amount);
    }

    @Test
    public void should_execute_print_of_printer_when_account_printStatement() {
        //given
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        Printer printer = mock(Printer.class);

        //when
        Account account = new Account(transactionRepository, printer);

        //then
        account.printStatement();
        verify(printer, times(1)).print(transactionRepository.allTransactions());
    }
}
