package com.example.project_3_software_methodology;

/**
 * CollegeChecking class represents a special type of checking account for college students, inheriting from Checking.
 * It includes properties and methods specific to college checking accounts.
 *
 * @author Rohan Parikh & Ganesh Konda
 */
public class CollegeChecking extends Checking {
    private static final double INTEREST_RATE = 0.01;
    private static final double FEE = 0.0;

    private Campus campus;
    private int c = 0;     // Code representing the campus

    /**
     * Constructor for creating a CollegeChecking account.
     *
     * @param holder  Profile of the account holder.
     * @param balance Initial balance of the account.
     * @param code    Numeric code representing the campus associated with the account.
     */
    public CollegeChecking(Profile holder, double balance, int code) {
        super(holder, balance);
        campus = Campus.getByCode(code);
        c = code; // Store the campus code
    }

    /**
     * Gets the account type of this college checking account.
     *
     * @return A string representing the account type ("CC" for College Checking).
     */
    @Override
    public String getAccountType() {
        return "CC";
    }

    /**
     * Gets the campus associated with the college checking account.
     *
     * @return The Campus enum constant representing the campus of the account.
     */
    public Campus getCampus() {
        return campus;
    }

    /**
     * Calculates the monthly interest for this college checking account.
     *
     * @return The monthly interest earned by the account.
     */
    @Override
    public double monthlyInterest() {
        return (balance * INTEREST_RATE) / 12;
    }

    /**
     * Calculates the monthly fee for this college checking account (always 0).
     *
     * @return The monthly fee for this college checking account (always 0).
     */
    @Override
    public double monthlyFee() {
        return FEE;
    }

    /**
     * Gets the Campus enum constant representing the campus code associated with the account.
     *
     * @return The Campus enum constant.
     */
    public Campus getCode() {
        return Campus.getByCode(c);
    }
}
