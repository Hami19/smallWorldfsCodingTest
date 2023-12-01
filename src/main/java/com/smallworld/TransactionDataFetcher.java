package com.smallworld;

import com.smallworld.data.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class TransactionDataFetcher {

    private static final Logger logger = LoggerFactory.getLogger(TransactionDataFetcher.class);

    private List<Transaction> transactions;

    public TransactionDataFetcher(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {
        try {
            logger.info("Starting getTotalTransactionAmount Method");

            double sum =  transactions.stream()
                    .mapToDouble(Transaction::getAmount)
                    .sum();

            logger.debug("Total Transaction Amount : ", sum);
            return sum;
        }
        catch (Exception e) {
           logger.error("Got Exception in getTotalTransactionAmount : ", e);
           throw e;
        }
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        try {
            logger.info("Starting getTotalTransactionAmountSentBy Method");
            logger.debug("Sender Full Name : ", senderFullName);

            double sum = transactions.stream()
                    .filter(transaction -> senderFullName.equals(transaction.getSenderFullName()))
                    .mapToDouble(Transaction::getAmount)
                    .sum();

            logger.debug("Total Transaction Amount : ", sum);
            return sum;
        }
        catch (Exception e) {
            logger.error("Got Exception in getTotalTransactionAmountSentBy : ", e);
            throw e;
        }
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
        try {
            logger.info("Starting getMaxTransactionAmount Method");

            double max = transactions.stream()
                    .mapToDouble(Transaction::getAmount)
                    .max()
                    .orElse(0.0);

            logger.debug("Max Transaction Amount : ", max);
            return max;
        }
        catch (Exception e) {
            logger.error("Got Exception in getMaxTransactionAmount : ", e);
            throw e;
        }
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
        public long countUniqueClients() {
        try {
            logger.info("Starting countUniqueClients Method");

            long totalCount =  transactions.stream()
                    .flatMap(transaction -> List.of(transaction.getSenderFullName(), transaction.getBeneficiaryFullName()).stream())
                    .distinct()
                    .count();

            logger.debug("Total Unique Client Count : ", totalCount);
            return totalCount;
        }
        catch (Exception e) {
            logger.error("Got Exception in countUniqueClients : ", e);
            throw e;
        }
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
        try {
            logger.info("Starting hasOpenComplianceIssues Method");
            logger.debug("Client Full name : ", clientFullName);

            boolean openCompliance = transactions.stream().anyMatch(transaction ->
                            clientFullName.equals(transaction.getSenderFullName()) ||
                                    clientFullName.equals(transaction.getBeneficiaryFullName()) &&
                                            !transaction.isIssueSolved());

            logger.debug("Client has open compliance : ", openCompliance);
            return openCompliance;
        }
        catch (Exception e) {
            logger.error("Got Exception in hasOpenComplianceIssues : ", e);
            throw e;
        }
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, List<Transaction>> getTransactionsByBeneficiaryName() {
        try {
            logger.info("Starting getTransactionsByBeneficiaryName Method");

            return transactions.stream()
                    .collect(Collectors.groupingBy(Transaction::getBeneficiaryFullName));
        }
        catch (Exception e) {
            logger.error("Got Exception in getTransactionsByBeneficiaryName : ", e);
            throw e;
        }
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
        try {
            logger.info("Starting getUnsolvedIssueIds Method");

            return transactions.stream()
                    .filter(transaction -> !transaction.isIssueSolved())
                    .map(Transaction::getIssueId)
                    .collect(Collectors.toSet());
        }
        catch (Exception e) {
            logger.error("Got Exception in getUnsolvedIssueIds : ", e);
            throw e;
        }
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
        try {
            logger.info("Starting getAllSolvedIssueMessages Method");

            return transactions.stream()
                    .filter(Transaction::isIssueSolved)
                    .map(Transaction::getIssueMessage)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            logger.error("Got Exception in getAllSolvedIssueMessages : ", e);
            throw e;
        }
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<Transaction> getTop3TransactionsByAmount() {
        try {
            logger.info("Starting getTop3TransactionsByAmount Method");

            return transactions.stream()
                    .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                    .limit(3)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            logger.error("Got Exception in getTop3TransactionsByAmount : ", e);
            throw e;
        }
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender() {
        try {
            logger.info("Starting getTopSender Method");

            return transactions.stream()
                    .collect(Collectors.groupingBy(Transaction::getSenderFullName, Collectors.summingDouble(Transaction::getAmount)))
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey);
        }
        catch (Exception e) {
            logger.error("Got Exception in getTopSender : ", e);
            throw e;
        }
    }

}
