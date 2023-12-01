package com.smallworld;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallworld.data.Transaction;
import com.sun.tools.javac.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionDataFetcherTest {

    private TransactionDataFetcher transactionDataFetcher;

    @BeforeEach
    void setUp() throws IOException {
        String fileName = "transactions.json";
        String filePath = new File(fileName).getAbsolutePath();

        ObjectMapper objectMapper = new ObjectMapper();

        Transaction[] transactionsArray = objectMapper.readValue(new File(filePath), Transaction[].class);
        List<Transaction> transactions = Arrays.asList(transactionsArray);
        transactionDataFetcher = new TransactionDataFetcher(transactions);
    }

    @Test
    void testGetTotalTransactionAmount() {
        double totalAmount = transactionDataFetcher.getTotalTransactionAmount();
        assertEquals(4371.37, totalAmount);
    }

    @Test
    void testGetTotalTransactionAmountSentBy() {
        double totalAmount = transactionDataFetcher.getTotalTransactionAmountSentBy("Billy Kimber");
        assertEquals(459.09, totalAmount);
    }

    @Test
    void testGetMaxTransactionAmount() {
        double amount = transactionDataFetcher.getMaxTransactionAmount();
        assertEquals(985.0, amount);
    }

    @Test
    void testCountUniqueClients() {
        long count = transactionDataFetcher.countUniqueClients();
        assertEquals(14, count);
    }

    @Test
    void testHasOpenComplianceIssues() {
        boolean isIssues = transactionDataFetcher.hasOpenComplianceIssues("Michael Gray");
        assertTrue(isIssues);
    }

    @Test
    void testGetTransactionsByBeneficiaryName() {
        Map<String, List<Transaction>> transactions = transactionDataFetcher.getTransactionsByBeneficiaryName();
        assertEquals(3, transactions.get("Michael Gray").size());
        assertEquals(2, transactions.get("Arthur Shelby").size());
    }

    @Test
    void testGetUnsolvedIssueIds() {
        Set<Integer> openIssues = transactionDataFetcher.getUnsolvedIssueIds();
        assertEquals(5, openIssues.size());
        assertTrue(openIssues.contains(99));
    }

    @Test
    void testGetAllSolvedIssueMessages() {
        List<String> messages = transactionDataFetcher.getAllSolvedIssueMessages();
        assertEquals(8, messages.size());
    }

    @Test
    void testGetTop3TransactionsByAmount() {
        List<Transaction> transactions = transactionDataFetcher.getTop3TransactionsByAmount();
        assertEquals(985.0,transactions.get(0).getAmount());
    }

    @Test
    void testGetTopSender() {
        Optional<String> topSender = transactionDataFetcher.getTopSender();
        assertTrue(topSender.isPresent());
        assertEquals("Grace Burgess",topSender.get());
    }

}
