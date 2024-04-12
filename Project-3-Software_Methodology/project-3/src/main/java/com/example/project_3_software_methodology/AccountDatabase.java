package com.example.project_3_software_methodology;

import java.text.DecimalFormat;

/**
 * AccountDatabase class represents a collection of accounts.
 * It provides methods to manage accounts, such as opening, closing, depositing, and withdrawing funds.
 *
 * @author Rohan Parikh & Ganesh Konda
 */

public class AccountDatabase {
    private static final int INITIAL_CAPACITY = 4;
    private static final int GROWTH_FACTOR = 4;
    private Account[] accounts;
    private int numAcct;

    /**
     * Constructor to initialize the AccountDatabase with an initial capacity.
     */
    public AccountDatabase() {
        accounts = new Account[INITIAL_CAPACITY];
        numAcct = 0;
    }

    /**
     * Private helper method to resize the accounts array when it reaches its capacity.
     */
    private void grow() {
        Account[] newAccounts = new Account[accounts.length + GROWTH_FACTOR];
        System.arraycopy(accounts, 0, newAccounts, 0, numAcct);
        accounts = newAccounts;
    }

    /**
     * Checks if the database contains a specific account.
     *
     * @param account The account to be checked.
     * @return true if the account is found, false otherwise.
     */
    public boolean contains(Account account) {
        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].equals(account)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Opens a new account and adds it to the database if it doesn't already exist.
     *
     * @param account The account to be opened and added.
     * @return true if the account is successfully opened and added, false otherwise.
     */
    public boolean open(Account account) {
        if (numAcct >= accounts.length) {
            grow();
        }
        if (!contains(account)) {
            accounts[numAcct] = account;
            numAcct++;
            return true;
        }
        return false;
    }

    /**
     * Closes an existing account and removes it from the database.
     *
     * @param account The account to be closed and removed.
     * @return true if the account is successfully closed and removed, false otherwise.
     */
    public boolean close(Account account) {
        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].equals(account)) {
                accounts[i] = accounts[numAcct - 1];
                accounts[numAcct - 1] = null;
                numAcct--;
                return true;
            }
        }
        return false;
    }

    /**
     * Withdraws funds from an existing account.
     *
     * @param account The account from which funds are withdrawn.
     * @param amount  The amount to be withdrawn.
     * @return true if the withdrawal is successful, false otherwise.
     */
    public boolean withdraw(Account account, double amount) {
        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].equals(account)) {
                if (accounts[i].getBalance() >= amount) {
                    accounts[i].setBalance(accounts[i].getBalance() - amount);
                    String accountType = accounts[i].getAccountType();
                    if(accountType.equals("MM")){
                        ((MoneyMarket) account).incrementWithdrawals();
                    }
                    return true;
                } else {
                   // System.out.println("Insufficient funds");
                    // Insufficient funds
                    return false;
                }
            }
        }
        return false; // Account not found
    }

    /**
     * Deposits funds into an existing account.
     *
     * @param account The account into which funds are deposited.
     * @param amount  The amount to be deposited.
     */
    public void deposit(Account account, double amount) {
        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].equals(account)) {
                accounts[i].setBalance(accounts[i].getBalance() + amount);
                return;
            }
        }
    }

    /**
     * Sorts the accounts in the database based on account type and holder's profile information using bubble sort algorithm.
     */
    public void bubbleSort(){
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < numAcct; i++) {
                int typeComparison = accounts[i - 1].getAccountType().compareTo(accounts[i].getAccountType());
                if (typeComparison == 0) {
                    int profileComparison = accounts[i - 1].getHolder().compareTo(accounts[i].getHolder());
                    if (profileComparison > 0) {
                        Account temp = accounts[i - 1];
                        accounts[i - 1] = accounts[i];
                        accounts[i] = temp;
                        swapped = true;
                    }
                } else if (typeComparison > 0) {
                    Account temp = accounts[i - 1];
                    accounts[i - 1] = accounts[i];
                    accounts[i] = temp;
                    swapped = true;
                }
            }
        } while (swapped);


    }
    /**
     * Prints the accounts in the database sorted by account type and holder's profile information.
     *
     * @return output to be printed in GUI
     */
    public String printSorted() {
        if (numAcct == 0) {
            return "Account Database is empty!\n";
        }

        bubbleSort();

        String output = "\n*Accounts sorted by account type and profile.\n";
        output += helperP();
        output += "*End of list.\n";

        return output;
    }

    /**
     * Helper Method: Prints the accounts in the database sorted by account type and holder's profile information.
     *
     * @return output to be printed in GUI
     */
    private String helperP() {
        String output = "";
        for (int i = 0; i < numAcct; i++) {
            Account account = accounts[i];
            String accountType = account.getAccountType();
            String balance = String.format("Balance $%.2f", account.getBalance());
            String dob = account.getHolder().getDateOfBirth().getMonth() + "/"
                    + account.getHolder().getDateOfBirth().getDay() + "/"
                    + account.getHolder().getDateOfBirth().getYear();
            String accountHolder = account.getHolder().getFirstName() + " " + account.getHolder().getLastName();
            String balanceInfo = formattedBalance(balance);

            if (accountType.equals("CC")) {
                if (account instanceof CollegeChecking) {
                    Campus campus = ((CollegeChecking) account).getCode();
                    output += "College Checking::" + accountHolder + " " + dob + "::" + balanceInfo + "::" + campus + "\n";
                }
            }
            else if (accountType.equals("C")) {
                if (account instanceof Checking) {
                    output += "Checking::" + accountHolder + " " + dob + "::" + balanceInfo + "\n";
                }
            }
            else if (accountType.equals("S")) {
                if (account instanceof Savings) {
                    boolean isLoyal = ((Savings) account).getIsLoyal();
                    String loyaltyInfo = isLoyal ? ":: is loyal" : " ";
                    output += "Savings::" + accountHolder + " " + dob + "::" + balanceInfo + loyaltyInfo + "\n";
                }
            }
            else if (accountType.equals("MM")) {
                if (account instanceof MoneyMarket) {
                    MoneyMarket mmAccount = (MoneyMarket) account;
                    boolean isLoyal = account.getBalance() >= 2000;
                    String loyaltyInfo = isLoyal ? ":: is loyal" : "";
                    output += "Money Market::" + accountHolder + " " + dob + "::" + balanceInfo + loyaltyInfo + ":: withdrawal :" + mmAccount.getWithdrawalCount() + "\n";
                }
            }
        }
        return output;
    }

    /**
     * Prints the list of accounts with corresponding fees and monthly interest rates applied.
     *
     * @return output to be printed in GUI
     */

    public String printFeesAndInterests() {
        if (numAcct == 0) {
            return "Account Database is empty!\n";
        }

        bubbleSort();
        String output = "\n*List of accounts with fee and monthly interest\n";

        for (int i = 0; i < numAcct; i++) {
            Account account = accounts[i];
            String accountType = account.getAccountType();
            String accountHolder = account.getHolder().getFirstName() + " " + account.getHolder().getLastName();
            String dob = account.getHolder().getDateOfBirth().getMonth() + "/"
                    + account.getHolder().getDateOfBirth().getDay() + "/"
                    + account.getHolder().getDateOfBirth().getYear();
            String balance = String.format("Balance $%.2f", account.getBalance());
            double interest = account.monthlyInterest();
            double fee = account.monthlyFee();
            String formattedFee = String.format("%.2f", fee);
            String formattedInterest = String.format("%.2f", interest);
            String formattedBalance = formattedBalance(balance);
            String accountTypeString = "";
            String additionalInfo = "";
            String loyaltyInfo = "";
            String campusInfo = "";

            switch (accountType) {
                case "MM":
                    if (account instanceof MoneyMarket) {
                        MoneyMarket mmAccount = (MoneyMarket) account;
                        additionalInfo = "::withdrawal: " + mmAccount.getWithdrawalCount();
                        accountTypeString = "Money Market";
                        boolean isLoyal = account.getBalance() >= 2000;
                        loyaltyInfo = isLoyal ? "::is loyal" : "";
                    }
                    break;
                case "S":
                    accountTypeString = "Savings";
                    if (account instanceof Savings) {
                        boolean isLoyal = ((Savings) account).getIsLoyal();
                        loyaltyInfo = isLoyal ? ":: is loyal" : " ";
                    }
                    break;
                case "C":
                    accountTypeString = "Checking";
                    break;
                case "CC":
                    accountTypeString = "College Checking";
                    if (account instanceof CollegeChecking) {
                        Campus campus = ((CollegeChecking) account).getCode();
                        campusInfo = "::" + campus;
                    }
                    break;
            }

            output += accountTypeString + "::" + accountHolder + " " + dob + "::" + formattedBalance
                    + campusInfo + loyaltyInfo + additionalInfo + "::fee $" + formattedFee
                    + "::monthly interest $" + formattedInterest + "\n";
        }

        output += "*End of list.\n";
        return output;
    }


    /**
     * Prints the list of accounts with updated balances after applying fees and interests.
     *
     * @return output to be printed in GUI
     */
    public String printUpdatedBalances() {
        if (numAcct == 0) {
            return "Account Database is empty!\n";
        }

        String output = "\n*List of accounts with fees and interests applied.\n";
        bubbleSort();
        output += helperUB();
        output += "*End of list.\n";

        return output;
    }

    /**
     * Helper Method : Prints the list of accounts with updated balances after applying fees and interests.
     *
     * @return output to be printed in GUI
     */
    private String helperUB() {
        String output = "";

        for (int i = 0; i < numAcct; i++) {
            Account account = accounts[i];
            String accountType = account.getAccountType();
            double interest = account.monthlyInterest();
            double fee = account.monthlyFee();
            double updatedBalance = account.getBalance() + interest - fee;
            String balance = String.format("Balance $%.2f", updatedBalance);
            String dob = account.getHolder().getDateOfBirth().getMonth() + "/" +
                    account.getHolder().getDateOfBirth().getDay() + "/" +
                    account.getHolder().getDateOfBirth().getYear();
            String accountHolder = account.getHolder().getFirstName() + " " + account.getHolder().getLastName();
            String balanceInfo = formattedBalance(balance);
            String accountDetails = "";

            switch (accountType) {
                case "CC":
                    if (account instanceof CollegeChecking) {
                        Campus campus = ((CollegeChecking) account).getCode();
                        accountDetails = "College Checking" + "::" + accountHolder + " " + dob + "::" + balanceInfo + "::" + campus;
                    }
                    break;
                case "C":
                    if (account instanceof Checking) {
                        accountDetails = "Checking" + "::" + accountHolder + " " + dob + "::" + balanceInfo;
                    }
                    break;
                case "S":
                    if (account instanceof Savings) {
                        boolean isLoyal = ((Savings) account).getIsLoyal();
                        String loyaltyInfo = isLoyal ? ":: is loyal" : " ";
                        accountDetails = "Savings" + "::" + accountHolder + " " + dob + "::" + balanceInfo + loyaltyInfo;
                    }
                    break;
                case "MM":
                    if (account instanceof MoneyMarket) {
                        MoneyMarket mmAccount = (MoneyMarket) account;
                        boolean isLoyal = account.getBalance() >= 2000;
                        String loyaltyInfo = isLoyal ? ":: is loyal" : " ";
                        accountDetails = "Money Market" + "::" + accountHolder + " " + dob + "::" + balanceInfo + loyaltyInfo + "::" + " " + "withdrawal :" +  mmAccount.getWithdrawalCount();
                    }
                    break;
            }

            output += accountDetails + "\n";
        }

        return output;
    }


    /**
     * Finds and returns an account based on the provided profile.
     *
     * @param profile The profile used to find the account.
     * @return The account associated with the provided profile, or null if not found.
     */
    public Account find(Profile profile) {
        for (int i = 0; i < numAcct; i++) {
            Account account = accounts[i];
            if (accounts[i].getHolder().equals(profile)) {
                return account;
            }
        }
        return null; // Account not found
    }

    /**
     * Finds and returns an account based on the provided profile and account type.
     *
     * @param profile      The profile used to find the account.
     * @param accountType  The type of account to be found.
     * @return The account associated with the provided profile and account type, or null if not found.
     */
    public Account find2(Profile profile, String accountType) {
        for (int i = 0; i < numAcct; i++) {
            Account account = accounts[i];
            if (account.getHolder().equals(profile) && account.getAccountType().equals(accountType)) {
                return account;
            }
        }
        return null; // Account not found
    }

    /**
     * Helper method to format the balance string.
     *
     * @param balanceStr The balance string to be formatted.
     * @return The formatted balance string.
     */
    private String formattedBalance(String balanceStr){
        String numericalPart = balanceStr.substring(9);
        double balance = Double.parseDouble(numericalPart);

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return "Balance $" + decimalFormat.format(balance);
    }



}
