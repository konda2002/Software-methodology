package com.example.project_3_software_methodology;
/**
 * Savings class represents a savings account, which is a type of Account.
 * It includes additional properties specific to savings accounts.
 *
 * @author Rohan Parikh & Ganesh Konda
 */
public class Savings extends Account {
    private static final double INTEREST_RATE = 0.04;
    private static final double FEE = 25.0;
    private boolean isLoyal;

    /**
     * Constructor for creating a Savings account.
     *
     * @param holder   Profile of the account holder.
     * @param balance  Initial balance of the account.
     * @param isLoyal  Boolean indicating whether the account holder is loyal.
     */
    public Savings(Profile holder, double balance, boolean isLoyal) {
        super(holder, balance); // Call the constructor of the parent class (Account)
        this.isLoyal = isLoyal; // Set the isLoyal property for this savings account
    }

    /**
     * Gets the account type of this savings account.
     *
     * @return A string representing the account type ("S" for Savings).
     */
    @Override
    public String getAccountType() {
        return "S";
    }

    /**
     * Checks if the account holder is loyal and calculates the monthly interest based on the balance.
     *
     * @return The monthly interest for this savings account.
     */
    @Override
    public double monthlyInterest() {
        return ((isLoyal ? 0.0425 : 0.04) * balance) / 12;
    }

    /**
     * Calculates the monthly fee for this savings account.
     *
     * @return The monthly fee for this savings account (0 if balance is 500 or more, otherwise FEE).
     */
    @Override
    public double monthlyFee() {
        return (balance >= 500) ? 0 : FEE;
    }

    /**
     * Gets the loyalty status of the account holder.
     *
     * @return true if the account holder is loyal, false otherwise.
     */
    public boolean getIsLoyal() {
        return isLoyal;
    }
}
