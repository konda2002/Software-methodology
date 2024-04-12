package com.example.project_3_software_methodology;

/**
 * MoneyMarket class represents a money market account, which is a type of Savings account.
 * It includes properties and methods specific to money market accounts.
 *
 * @author Rohan Parikh & Ganesh Konda
 */
public class MoneyMarket extends Savings {
    private static final double INTEREST_RATE = 0.045;
    private static final double FEE = 25.0;
    private static final int WITHDRAWAL_LIMIT = 3;

    private int withdrawals;  // Count of withdrawals made from the account

    /**
     * Constructor for creating a MoneyMarket account.
     *
     * @param holder   Profile of the account holder.
     * @param balance  Initial balance of the account.
     * @param isLoyal  Boolean indicating whether the account holder is loyal (eligible for higher interest rate).
     */
    public MoneyMarket(Profile holder, double balance, boolean isLoyal) {
        super(holder, balance, isLoyal);
        this.withdrawals = 0;
    }

    /**
     * Gets the account type of this money market account.
     *
     * @return A string representing the account type ("MM" for Money Market).
     */
    @Override
    public String getAccountType() {
        return "MM";
    }

    /**
     * Gets the count of withdrawals made from the account.
     *
     * @return The number of withdrawals made from the account.
     */
    public int getWithdrawalCount() {
        return withdrawals;
    }

    /**
     * Calculates the monthly interest for this money market account based on the balance and interest rate.
     *
     * @return The monthly interest earned by the account.
     */
    @Override
    public double monthlyInterest() {
        return (balance >= 2000) ? (0.0475 * balance) / 12 : (0.045 * balance) / 12;
    }

    /**
     * Calculates the monthly fee for this money market account based on balance, withdrawal count, and fees.
     *
     * @return The total monthly fee for the account.
     */
    @Override
    public double monthlyFee() {
        double fees;
        if (balance >= 2000) {
            fees = 0;
        } else {
            fees = FEE;
        }
        if (withdrawals > WITHDRAWAL_LIMIT) {
            fees += (withdrawals - WITHDRAWAL_LIMIT) * 10;
        }
        return fees;
    }

    /**
     * Increments the withdrawal count when a withdrawal is made from the account.
     */
    public void incrementWithdrawals() {
        withdrawals++;
    }
}
