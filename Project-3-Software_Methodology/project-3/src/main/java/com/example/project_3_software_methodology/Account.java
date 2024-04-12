package com.example.project_3_software_methodology;

/**
 * Abstract class representing an account. All specific account types (like Savings and Checking) will inherit from this class.
 * Implements Comparable interface for comparing accounts based on holders' profiles.
 *
 * @author Rohan Parikh & Ganesh Konda
 */
public abstract class Account implements Comparable<Account> {
    protected Profile holder;  // Profile of the account holder
    protected double balance;  // Balance of the account

    /**
     * Constructor for creating an Account object.
     *
     * @param holder  Profile of the account holder.
     * @param balance Initial balance of the account.
     */
    public Account(Profile holder, double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    /**
     * Abstract method to be implemented by subclasses. Returns the account type.
     *
     * @return A string representing the account type.
     */
    public abstract String getAccountType();

    /**
     * Getter method for retrieving the account holder's profile.
     *
     * @return The Profile object representing the account holder.
     */
    public Profile getHolder() {
        return holder;
    }

    /**
     * Getter method for retrieving the account balance.
     *
     * @return The balance of the account.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Setter method for updating the account balance.
     *
     * @param balance The new balance of the account.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Abstract method to be implemented by subclasses. Calculates and returns the monthly interest for the account.
     *
     * @return The monthly interest earned by the account.
     */
    public abstract double monthlyInterest();

    /**
     * Abstract method to be implemented by subclasses. Calculates and returns the monthly fee for the account.
     *
     * @return The monthly fee charged by the account.
     */
    public abstract double monthlyFee();

    /**
     * Compares this account with another account based on holders' profiles.
     *
     * @param other Another Account object to be compared.
     * @return Negative integer if this account is less than the other, zero if they are equal, positive integer if greater.
     */
    @Override
    public int compareTo(Account other) {
        return this.holder.compareTo(other.holder);
    }

    /**
     * Checks if this account is equal to another object based on their balances and holders' profiles.
     *
     * @param obj Another object to compare with this account.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Account account = (Account) obj;
        return Double.compare(account.balance, balance) == 0 &&
                holder.equals(account.holder);
    }
}
