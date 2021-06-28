package com.tw.banking;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PrinterTest {

    private List<Transaction> generateTransactionList() {
        Transaction transaction1 = new Transaction("28/06/2021", 100);
        Transaction transaction2 = new Transaction("28/06/2021", 200);
        return Arrays.asList(transaction1, transaction2);
    }

    @Test
    public void should_print_statement_with_right_format_when_print() {
        //given
        Console console = mock(Console.class);
        Printer printer = new Printer(console);
        List<Transaction> transactions = generateTransactionList();

        //when
        printer.print(transactions);

        //then
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(console, times(3)).printLine(stringArgumentCaptor.capture());
        List<String> actualArguments = stringArgumentCaptor.getAllValues();

        assertEquals(Printer.STATEMENT_HEADER, actualArguments.get(0));
        assertEquals("28/06/2021 | 200 | 300", actualArguments.get(1));
        assertEquals("28/06/2021 | 100 | 100", actualArguments.get(2));
    }

    @Test
    public void should_print_statement_header_when_print_without_transactions() {
        //given
        Console console = mock(Console.class);
        Printer printer = new Printer(console);
        List<Transaction> transactions = new ArrayList<>();

        //when
        printer.print(transactions);

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(console, times(1)).printLine(stringArgumentCaptor.capture());
        List<String> actualArguments = stringArgumentCaptor.getAllValues();

        //then
        assertEquals(Printer.STATEMENT_HEADER, actualArguments.get(0));
        assertEquals(1, actualArguments.size());
    }


}