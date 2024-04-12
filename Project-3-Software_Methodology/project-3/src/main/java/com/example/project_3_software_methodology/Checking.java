package com.example.project_3_software_methodology;

/**
 * Checking class represents a checking account, which is a type of Account.
 * It includes properties and methods specific to checking accounts.
 *
 * @author Rohan Parikh & Ganesh Konda
 */
public class Checking extends Account {
    // Constants for interest rate and fee
    private static final double INTEREST_RATE = 0.01;
    private static final double FEE = 12.0;

    /**
     * Constructor for creating a Checking account.
     *
     * @param holder  Profile of the account holder.
     * @param balance Initial balance of the account.
     */
    public Checking(Profile holder, double balance) {
        super(holder, balance); // Call the constructor of the parent class (Account)
    }

    /**
     * Gets the account type of this checking account.
     *
     * @return A string representing the account type ("C" for Checking).
     */
    @Override
    public String getAccountType() {
        return "C";
    }

    /**
     * Calculates the monthly interest for this checking account.
     *
     * @return The monthly interest earned by the account.
     */
    @Override
    public double monthlyInterest() {
        return (balance * INTEREST_RATE) / 12;
    }

    /**
     * Calculates the monthly fee for this checking account.
     *
     * @return The monthly fee for this checking account (0 if balance is 1000 or more, otherwise FEE).
     */
    @Override
    public double monthlyFee() {
        return (balance >= 1000) ? 0 : FEE;
    }
}


