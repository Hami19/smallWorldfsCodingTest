package com.smallworld.data;

public class Transaction {
    // Represent your transaction data here.
    private int mtn;
    private double amount;
    private String senderFullName;
    private int senderAge;
    private String beneficiaryFullName;
    private int beneficiaryAge;
    private Integer issueId;
    private boolean issueSolved;
    private String issueMessage;

    public int getMtn() {
        return mtn;
    }

    public Transaction setMtn(int mtn) {
        this.mtn = mtn;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public Transaction setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public String getSenderFullName() {
        return senderFullName;
    }

    public Transaction setSenderFullName(String senderFullName) {
        this.senderFullName = senderFullName;
        return this;
    }

    public int getSenderAge() {
        return senderAge;
    }

    public Transaction setSenderAge(int senderAge) {
        this.senderAge = senderAge;
        return this;
    }

    public String getBeneficiaryFullName() {
        return beneficiaryFullName;
    }

    public Transaction setBeneficiaryFullName(String beneficiaryFullName) {
        this.beneficiaryFullName = beneficiaryFullName;
        return this;
    }

    public int getBeneficiaryAge() {
        return beneficiaryAge;
    }

    public Transaction setBeneficiaryAge(int beneficiaryAge) {
        this.beneficiaryAge = beneficiaryAge;
        return this;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public Transaction setIssueId(Integer issueId) {
        this.issueId = issueId;
        return this;
    }

    public boolean isIssueSolved() {
        return issueSolved;
    }

    public Transaction setIssueSolved(boolean issueSolved) {
        this.issueSolved = issueSolved;
        return this;
    }

    public String getIssueMessage() {
        return issueMessage;
    }

    public Transaction setIssueMessage(String issueMessage) {
        this.issueMessage = issueMessage;
        return this;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "mtn=" + mtn +
                ", amount=" + amount +
                ", senderFullName='" + senderFullName + '\'' +
                ", senderAge=" + senderAge +
                ", beneficiaryFullName='" + beneficiaryFullName + '\'' +
                ", beneficiaryAge=" + beneficiaryAge +
                ", issueId=" + issueId +
                ", issueSolved=" + issueSolved +
                ", issueMessage='" + issueMessage + '\'' +
                '}';
    }
}
