package com.example.project_3_software_methodology;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ToggleGroup;
import javafx.beans.value.ObservableValue;
import javafx.stage.FileChooser;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * The TransactionController class handles user input commands from GUI and manages transactions for the AccountDatabase.
 *
 * @author Rohan Parikh & Ganesh Konda
 */

public class TransactionManagerController {

    private String accountType;

    private String accountType1;

    private String accountType2;

    private int campusCode = -1;

    private boolean isLoyalCheck;

    private AccountDatabase accountDB;

    /**
     * Constructor to initialize the TransactionManager with an AccountDatabase.
     *
     */
    public TransactionManagerController() {

    }

    /**
     * Constructor to initialize the TransactionManager with an AccountDatabase.
     *
     * @param accountDB The AccountDatabase instance to be used.
     */
    public TransactionManagerController(AccountDatabase accountDB) {
        this.accountDB = accountDB;
    }

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField firstNameTextField1;

    @FXML
    private TextField firstNameTextField2;

    @FXML
    private TextArea outputArea;

    @FXML
    private TextArea outputArea1;

    @FXML
    private TextArea outputArea2;

    @FXML
    private TextArea outputArea3;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField lastNameTextField1;

    @FXML
    private TextField lastNameTextField2;

    @FXML
    private TextField initialDepositTextField;

    @FXML
    private TextField amountTextField;

    @FXML
    private DatePicker dobDatePicker;

    @FXML
    private DatePicker dobDatePicker1;

    @FXML
    private DatePicker dobDatePicker2;

    @FXML
    private CheckBox isLoyalCheckbox;

    @FXML
    private RadioButton checkingRadioButton;

    @FXML
    private RadioButton collegeCheckingRadioButton;

    @FXML
    private RadioButton nbRadioButton;

    @FXML
    private RadioButton newarkRadioButton;

    @FXML
    private RadioButton camdenRadioButton;

    @FXML
    private ToggleGroup accountTypeGroup;

    @FXML
    private ToggleGroup accountTypeGroup1;

    @FXML
    private ToggleGroup accountTypeGroup2;

    @FXML
    private ToggleGroup campusLocationGroup;

    @FXML
    private MenuButton fileMenuButton;



    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded. It sets up the AccountDatabase,
     * disables all radio buttons within the campusLocationGroup initially,
     * and sets up listeners for UI components to handle user interactions
     * and input data accordingly.
     *
     */
    @FXML
    private void initialize() {
        accountDB = new AccountDatabase();

        campusLocationGroup.getToggles().forEach(toggle -> {
            RadioButton rb = (RadioButton) toggle;
            rb.setDisable(true);
        });

        campusLocationGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedRadioButton = (RadioButton) newValue;
                campusCode = getCampusCode(selectedRadioButton.getId());
            }
        });

        isLoyalCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                isLoyalCheck = true;
            } else {
                isLoyalCheck = false;
            }
        });
        accountTypeGroupListener();
        accountTypeGroup1Listener();
        accountTypeGroup2Listener();
    }

    /**
     * Sets up a listener for the accountTypeGroup toggle group. When a user selects
     * a different account type, this listener updates the accountType variable.
     */
    private void accountTypeGroupListener(){
        accountTypeGroup.selectedToggleProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    RadioButton selectedRadioButton = (RadioButton) newValue;
                    switch (selectedRadioButton.getId()) {
                        case "checkingRadioButton":
                            accountType = "C";
                            setCampusLocationGroupEnabled(false);
                            isLoyalCheckbox.setDisable(true);
                            isLoyalCheckbox.setSelected(false);
                            break;
                        case "collegeCheckingRadioButton":
                            accountType = "CC";
                            setCampusLocationGroupEnabled(true);
                            isLoyalCheckbox.setDisable(true);
                            isLoyalCheckbox.setSelected(false);
                            break;
                        case "savingsRadioButton":
                            accountType = "S";
                            setCampusLocationGroupEnabled(false);
                            isLoyalCheckbox.setDisable(false);
                            break;
                        case "moneyMarketRadioButton":
                            accountType = "MM";
                            setCampusLocationGroupEnabled(false);
                            isLoyalCheckbox.setDisable(true);
                            isLoyalCheckbox.setSelected(true);
                            break;
                        default:
                            accountType = "";
                            break;
                    }
                }
            }
        });
    }

    /**
     * Sets up a listener for the accountTypeGroup1 toggle group. When a user selects
     * a different account type, this listener updates the accountType1 variable.
     */
    private void accountTypeGroup1Listener(){
        accountTypeGroup1.selectedToggleProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    RadioButton selectedRadioButton = (RadioButton) newValue;
                    switch (selectedRadioButton.getId()) {
                        case "checkingRadioButton1":
                            accountType1 = "C";
                            break;
                        case "collegeCheckingRadioButton1":
                            accountType1 = "CC";
                            break;
                        case "savingsRadioButton1":
                            accountType1 = "S";
                            break;
                        case "moneyMarketRadioButton1":
                            accountType1 = "MM";
                            break;
                        default:
                            accountType1 = "";
                            break;
                    }
                }
            }
        });
    }

    /**
     * Sets up a listener for the accountTypeGroup2 toggle group. When a user selects
     * a different account type, this listener updates the accountType2 variable.
     */
    private void accountTypeGroup2Listener(){
        accountTypeGroup2.selectedToggleProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    RadioButton selectedRadioButton = (RadioButton) newValue;
                    switch (selectedRadioButton.getId()) {
                        case "checkingRadioButton2":
                            accountType2 = "C";
                            break;
                        case "collegeCheckingRadioButton2":
                            accountType2 = "CC";
                            break;
                        case "savingsRadioButton2":
                            accountType2 = "S";
                            break;
                        case "moneyMarketRadioButton2":
                            accountType2 = "MM";
                            break;
                        default:
                            accountType2 = "";
                            break;
                    }
                }
            }
        });
    }


    /**
     * Enables or disables all radio buttons within the campusLocationGroup and clears
     * the selection if they are being disabled.
     * @param isEnabled Flag indicating whether the campus location group should be enabled.
     */
    private void setCampusLocationGroupEnabled(boolean isEnabled) {
        campusLocationGroup.getToggles().forEach(toggle -> {
            RadioButton rb = (RadioButton) toggle;
            rb.setDisable(!isEnabled);

            if (!isEnabled) {
                campusLocationGroup.selectToggle(null);
            }
        });
    }

    /**
     * Retrieves the campus code associated with a given radio button ID.
     * @param radioButtonId The ID of the radio button to look up.
     * @return An int representing the campus code, or -1 if the ID does not match a campus.
     */
    private int getCampusCode(String radioButtonId) {
        // Match the radio button ID to the corresponding campus code
        switch (radioButtonId) {
            case "nbRadioButton":
                return 0;
            case "newarkRadioButton":
                return 1;
            case "camdenRadioButton":
                return 2;
            default:
                return -1;
        }
    }

    /**
     * Displays a warning if required fields are not filled in for opening an account.
     *
     */
    private void openAccountWarning() {
        if (firstNameTextField.getText().trim().isEmpty() || lastNameTextField.getText().trim().isEmpty() ||
                dobDatePicker.getValue() == null ||
                initialDepositTextField.getText().trim().isEmpty()) {
            showAlert("Warning", "All fields must be filled out to open an account.");
            return;
        }

    }

    /**
     * Displays a warning if required fields are not filled in for closing an account.
     *
     */
    private void closeAccountWarning(){
        if (firstNameTextField1.getText().trim().isEmpty() || lastNameTextField1.getText().trim().isEmpty() ||
                dobDatePicker1.getValue() == null) {
            showAlert("Warning", "All fields must be filled out to close an account.");
            return;
        }
    }

    /**
     * Displays a warning if required fields are not filled in for depositing or withdrawing from an account.
     *
     */
    private void deposit_withdrawAccountWarning(){
        if (firstNameTextField2.getText().trim().isEmpty() || lastNameTextField2.getText().trim().isEmpty() ||
                dobDatePicker2.getValue() == null) {
            showAlert("Warning", "All fields must be filled out to deposit/withdraw from an account.");
            return;
        }

        if(amountTextField.getText().trim().isEmpty()){
            showAlert("Warning", "Please enter an amount.");
            return;
        }
    }

    /**
     * This method is triggered by the "Open Account" button click event.
     * It gathers all necessary data, performs preliminary warnings, and proceeds to open an account.
     * If successful, it will clear the input fields after account creation.
     */
    @FXML
    private void handleOpenAccountButtonClick() {

        openAccountWarning();

        String accountType1 = accountType;
        String firstName = firstNameTextField.getText().trim();
        String lastName = lastNameTextField.getText().trim();
        String initialDepositString = initialDepositTextField.getText().trim();
        LocalDate dob = dobDatePicker.getValue();

        Date dobDate = new Date(dob.getYear(), dob.getMonthValue(), dob.getDayOfMonth());

        if (firstName.isEmpty() || lastName.isEmpty() || dob == null || initialDepositString.isEmpty()) {
            duplicateOutputText("Missing data for opening an account.\n");
            return;
        }
        if (accountTypeGroup.getSelectedToggle() == null) {
            showAlert("Warning", "Please select an account type.");
            duplicateOutputText("Missing data for opening an account.\n");
            return;
        }


        int campusCode1 = campusCode;
        boolean isLoyal = isLoyalCheck;

        openAccount(accountType1, firstName, lastName, dobDate, initialDepositString, campusCode, isLoyal);
        handleClearButtonClick();
    }


    /**
     * This method attempts to open a new account with the given parameters.
     * It performs various checks and validations on the data provided before
     * creating the account.
     *
     * @param accountType            the type of account to be opened
     * @param fname                  the first name of the account holder
     * @param lname                  the last name of the account holder
     * @param dob                    the date of birth of the account holder
     * @param initialDepositString   the initial deposit amount as a string
     * @param campusCode             the campus code for college checking accounts
     * @param isLoyal                loyalty status for savings accounts
     */
    private void openAccount(String accountType, String fname, String lname, Date dob, String initialDepositString, int campusCode, boolean isLoyal) {
        String firstName = fname.toUpperCase();
        String lastName = lname.toUpperCase();
        String dobString = dob.getMonth() + "/" + dob.getDay() + "/" + dob.getYear();
        if (dob == null || !isValidDate(dob)) {
            return;
        }

        if ("CC".equals(accountType)) {
            if (!checkUnder24ForCC(dob)) {
                return;
            }
            if (campusCode != 0 && campusCode != 1 && campusCode != 2) {
                duplicateOutputText("Invalid Campus code!\n");
                return;
            }
        }
        Profile profileToCheck = new Profile(firstName, lastName, dob);
        Account existingAccount = accountDB.find(profileToCheck);

        if (existingAccount != null) {
            if (("CC".equals(accountType) && existingAccount instanceof Checking) ||
                    ("C".equals(accountType) && existingAccount instanceof CollegeChecking)) {
                duplicateOutputText(fname + " " + lname + " " + dobString + " (" + accountType + ") is already in the database.\n");
                return;
            }
        }
        if (!initialDepositString.matches("-?\\d+(\\.\\d+)?")) {
            duplicateOutputText("Not a valid amount.\n");
            return;
        }

        double initialDeposit = Double.parseDouble(initialDepositString);

        if (initialDeposit <= 0) {
            duplicateOutputText("Initial deposit cannot be 0 or negative.\n");
            return;
        }
        Account account = createAccount(accountType, profileToCheck, initialDeposit, campusCode, isLoyal);
        openCommandOutput(account,fname,lname,dobString,accountType,initialDeposit);
    }

    /**
     * This method handles the output of openAccount .
     *
     * @param accountType            the type of account to be opened
     * @param fname                  the first name of the account holder
     * @param lname                  the last name of the account holder
     * @param dobString              the date of birth of the account holder
     * @param initialDeposit         the initial deposit amount as a string
     * @param account                the account
     */
    private void openCommandOutput(Account account, String fname, String lname, String dobString, String accountType, double initialDeposit){
        if (account == null) {
            if(accountType.equals("MM") && initialDeposit<2000){
                return;
            }
            duplicateOutputText("Invalid account type. Please check your input.\n");
            return;
        }
        if (accountDB.open(account)) {
            duplicateOutputText(fname + " " + lname + " " + dobString + " (" + accountType + ") opened.\n");
        } else {
            duplicateOutputText(fname + " " + lname + " " + dobString + " (" + accountType + ") is already in the database.\n");
        }
    }



    /**
     * Creates an account object based on the type of account to be opened.
     *
     * @param accountType      the type of account to be created
     * @param profile          the profile of the account holder
     * @param initialDeposit   the initial deposit amount
     * @param campusCode       the campus code for college checking accounts
     * @param isLoyal          loyalty status for savings accounts
     * @return                 the newly created Account object or null if the account cannot be created
     */
    private Account createAccount(String accountType, Profile profile, double initialDeposit, int campusCode, boolean isLoyal) {
        switch (accountType) {
            case "C":
                return new Checking(profile, initialDeposit);
            case "CC":
                return new CollegeChecking(profile, initialDeposit, campusCode);
            case "S":
                return new Savings(profile, initialDeposit, isLoyal);
            case "MM":
                if (initialDeposit < 2000) {
                    duplicateOutputText("Minimum of $2000 to open a Money Market account.\n");
                    return null;
                }
                return new MoneyMarket(profile, initialDeposit, true);
            default:
                return null;
        }
    }

    /**
     * Invoked when the "Close Account" button is clicked.
     * It checks for all required inputs and proceeds with account closure if validation passes.
     */
    @FXML
    private void handleCloseAccountButtonClick() {
        closeAccountWarning();
        String firstName = firstNameTextField1.getText().trim();
        String lastName = lastNameTextField1.getText().trim();
        LocalDate dob = dobDatePicker1.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() || dob == null) {
            duplicateOutputText("Missing data for closing an account.\n");
            return;
        }
        if (accountTypeGroup1.getSelectedToggle() == null) {
            showAlert("Warning", "Please select an account type.");
            duplicateOutputText("Missing data for closing an account.\n");
            return;
        }
        String fname = firstName.toUpperCase();
        String lname = lastName.toUpperCase();
        Date dobDate = new Date(dob.getYear(), dob.getMonthValue(), dob.getDayOfMonth());
        String dobString = dobDate.getMonth() + "/" + dobDate.getDay() + "/" + dobDate.getYear();

        if (!isValidDate(dobDate)) {
            duplicateOutputText("Invalid date of birth format. Please enter a valid date.\n");
            return;
        }
        Profile profile = new Profile(fname, lname, dobDate);
        Account account = accountDB.find2(profile,accountType1);
        if (account != null) {
            boolean canCloseAccount = canCloseAccount(account);
            if (canCloseAccount) {
                if (accountDB.close(account)) {
                    duplicateOutputText(firstName + " " + lastName + " " + dobString + " (" + accountType1 + ") has been closed.\n");
                    handleClearButtonClick();
                } else {
                    duplicateOutputText("Failed to close account. Please check your input.\n");
                }
            } else {
                duplicateOutputText(firstName + " " + lastName + " " + dobString + " (" + accountType1 + ") not in the database.\n");
            }
        } else {
            duplicateOutputText(firstName + " " + lastName + " " + dobString + " (" + accountType1 + ") not in the database.\n");}
    }

    /**
     * Determines if an account is eligible to be closed based on its type.
     *
     * @param account The account object to be checked.
     * @return A boolean indicating if the account can be closed.
     */
    private boolean canCloseAccount(Account account){

        boolean canCloseAccount = false;
        if (accountType1.equals("C") && account instanceof Checking) {
            canCloseAccount = true;
            return canCloseAccount;
        } else if (accountType1.equals("CC") && account instanceof CollegeChecking) {
            canCloseAccount = true;
            return canCloseAccount;
        } else if (accountType1.equals("S") && account instanceof Savings) {
            canCloseAccount = true;
            return canCloseAccount;
        } else if (accountType1.equals("MM") && account instanceof MoneyMarket) {
            canCloseAccount = true;
            return canCloseAccount;
        }
        return canCloseAccount;
    }

    /**
     * Clears all input fields and resets selections when the "Clear" button is clicked.
     */
    @FXML
    private void handleClearButtonClick() {
        firstNameTextField.clear();
        lastNameTextField.clear();
        initialDepositTextField.clear();
        dobDatePicker.setValue(null);
        firstNameTextField2.clear();
        firstNameTextField1.clear();
        lastNameTextField1.clear();
        lastNameTextField2.clear();
        dobDatePicker1.setValue(null);
        dobDatePicker2.setValue(null);
        isLoyalCheckbox.setSelected(false);
        amountTextField.clear();

        if (accountTypeGroup != null && accountTypeGroup.getSelectedToggle() != null) {
            accountTypeGroup.getSelectedToggle().setSelected(false);
        }
        if (accountTypeGroup1 != null && accountTypeGroup1.getSelectedToggle() != null) {
            accountTypeGroup1.getSelectedToggle().setSelected(false);
        }
        if (accountTypeGroup2 != null && accountTypeGroup2.getSelectedToggle() != null) {
            accountTypeGroup2.getSelectedToggle().setSelected(false);
        }
        if (campusLocationGroup != null && campusLocationGroup.getSelectedToggle() != null) {
            campusLocationGroup.getSelectedToggle().setSelected(false);
        }

        disableCampusLocationOptions(true);
    }

    /**
     * Enables or disables radio buttons for campus location based on the given parameter.
     *
     * @param disable A boolean that if true, disables the campus location radio buttons.
     */
    private void disableCampusLocationOptions(boolean disable) {
        nbRadioButton.setDisable(disable);
        newarkRadioButton.setDisable(disable);
        camdenRadioButton.setDisable(disable);
    }



    /**
     * Parses the input date string into a Date object.
     *
     * @param dateString The input date string in the format "MM/DD/YYYY".
     * @return A Date object representing the parsed date, or null if the format is invalid.
     */
    private Date parseDate(String dateString) {
        try {
            String[] parts = dateString.split("/");
            if (parts.length == 3) {
                int month = Integer.parseInt(parts[0]);
                int day = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[2]);

                return new Date(year, month, day);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
        }
        return null;
    }


    /**
     * Validates if the given date of birth is a valid and allowed date.
     *
     * @param eventDate The Date object representing the date of birth.
     * @return True if the date is valid, false otherwise.
     */
    private boolean isValidDate(Date eventDate) {
        boolean check = true;
        if (!eventDate.isValid()) {
            duplicateOutputText("DOB invalid: " + eventDate.getMonth() + "/" + eventDate.getDay() + "/" + eventDate.getYear() + " not a valid calendar date!\n");
            check = false;
            return check;
        }

        if (isFutureOrTodayDate(eventDate.getMonth(), eventDate.getDay(), eventDate.getYear())) {
            duplicateOutputText("DOB invalid: " + eventDate.getMonth() + "/" + eventDate.getDay() + "/" + eventDate.getYear() + " cannot be today or a future date!\n");
            check = false;
            return check;
        }

        if (isUnder16(eventDate.getMonth(), eventDate.getDay(), eventDate.getYear())) {
            duplicateOutputText("DOB invalid: " + eventDate.getMonth() + "/" + eventDate.getDay() + "/" + eventDate.getYear() + " under 16.\n");
            check = false;
            return check;
        }

        return check;
    }

    /**
     * Checks if the given date of birth is under 24 years old, specifically for College Checking accounts.
     *
     * @param dob The Date object representing the date of birth.
     * @return True if the person is under 24 years old, false otherwise.
     */
    private boolean checkUnder24ForCC(Date dob) {
        boolean check = true;
        if (!isUnder24(dob.getMonth(), dob.getDay(), dob.getYear())) {
            duplicateOutputText("DOB invalid: " + dob.getMonth() + "/" + dob.getDay() + "/" + dob.getYear() + " over 24.\n");
            check = false;
            return check;
        }
        return check;
    }

    /**
     * Checks if the person is under 16 years old based on the given date of birth.
     *
     * @param month The month of birth (1 to 12).
     * @param day   The day of birth (1 to 31).
     * @param year  The year of birth.
     * @return True if the person is under 16 years old, false otherwise.
     */
    private boolean isUnder16(int month, int day, int year) {
        Calendar minAge = Calendar.getInstance(); // Current date and time
        Calendar dob = Calendar.getInstance();
        dob.set(year, month - 1, day); // Month is 0-based in Calendar class
        minAge.add(Calendar.YEAR, -16);
        return dob.after(minAge);
    }

    /**
     * Checks if the given date is today or a future date.
     *
     * @param month The month of the date (1 to 12).
     * @param day   The day of the date (1 to 31).
     * @param year  The year of the date.
     * @return True if the date is today or a future date, false otherwise.
     */
    private boolean isFutureOrTodayDate(int month, int day, int year) {
        Calendar today = Calendar.getInstance(); // Current date and time
        today.add(Calendar.DAY_OF_MONTH, -1); // Set today to yesterday
        Calendar dob = Calendar.getInstance();
        dob.set(year, month - 1, day); // Month is 0-based in Calendar class
        return dob.after(today);
    }

    /**
     * Checks if the person is under 24 years old based on the given date of birth.
     *
     * @param month The month of birth (1 to 12).
     * @param day   The day of birth (1 to 31).
     * @param year  The year of birth.
     * @return True if the person is under 24 years old, false otherwise.
     */
    private boolean isUnder24(int month, int day, int year) {
        Calendar dob = Calendar.getInstance();
        dob.set(year, month - 1, day); // Month is 0-based in Calendar class
        Calendar over23YearsAgo = Calendar.getInstance();
        over23YearsAgo.add(Calendar.YEAR, -24);
        return !dob.before(over23YearsAgo);
    }

    /**
     * This method is executed when the "Withdraw" button is clicked. It initiates the withdrawal process.
     */
    @FXML
    private void handleWithdrawButtonClick() {

        deposit_withdrawAccountWarning();

        String firstName = firstNameTextField2.getText().trim();
        String lastName = lastNameTextField2.getText().trim();
        String withdrawAmountString = amountTextField.getText().trim();
        LocalDate dob = dobDatePicker2.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() || withdrawAmountString.isEmpty()) {
            duplicateOutputText("Missing data for withdrawing from an account.\n");
            return;
        }

        if (dob == null) {
            duplicateOutputText("Invalid date of birth.\n");
            return;
        }

        if (accountTypeGroup2.getSelectedToggle() == null) {
            showAlert("Warning", "Please select an account type.");
            duplicateOutputText("Missing data for withdrawing from an account.\n");
            return;
        }

        Date dobDate = new Date(dob.getYear(), dob.getMonthValue(), dob.getDayOfMonth());
        double withdrawalAmount;

        try {
            withdrawalAmount = parseWithdrawalAmount(withdrawAmountString);
        } catch (IllegalArgumentException e) {
            duplicateOutputText("Not a valid amount.\n");
            return;
        }

        handleWithdrawCommand(firstName, lastName, dobDate, withdrawalAmount);
        handleClearButtonClick();
    }

    /**
     * Parses the withdrawal amount from a string and throws an IllegalArgumentException if the format is invalid.
     *
     * @param amountToken The string to be parsed.
     * @return The parsed double value of the withdrawal amount.
     * @throws IllegalArgumentException if the amountToken is not a valid number format.
     */
    private double parseWithdrawalAmount(String amountToken) throws IllegalArgumentException {
        if (!amountToken.matches("-?\\d+(\\.\\d+)?")) {
            throw new IllegalArgumentException();
        }
        return Double.parseDouble(amountToken);
    }

    /**
     * Handles the withdrawal command by finding the account and attempting to withdraw the specified amount.
     *
     * @param firstName The first name of the account holder.
     * @param lastName The last name of the account holder.
     * @param dob The date of birth of the account holder.
     * @param withdrawalAmount The amount to withdraw from the account.
     */
    private void handleWithdrawCommand(String firstName, String lastName, Date dob, double withdrawalAmount) {
        String fName = firstName.toUpperCase();
        String lName = lastName.toUpperCase();
        String dobString = dob.getMonth() + "/" + dob.getDay() + "/" + dob.getYear();

        Profile profile = new Profile(fName, lName, dob);
        Account account = accountDB.find2(profile, accountType2);

        if (account == null) {
            duplicateOutputText(firstName + " " + lastName + dobString + "(" + accountType2 +") is not in the database.\n");
            return;
        }

        executeWithdrawal(account, withdrawalAmount, firstName, lastName, dobString);
    }

    /**
     * Executes the withdrawal transaction, updating the account balance if successful.
     *
     * @param account The account from which funds will be withdrawn.
     * @param withdrawalAmount The amount of funds to withdraw.
     * @param firstName The first name of the account holder.
     * @param lastName The last name of the account holder.
     */
    private void executeWithdrawal(Account account, double withdrawalAmount, String firstName, String lastName, String dobString) {
        if (withdrawalAmount <= 0) {
            duplicateOutputText("Withdraw - amount cannot be 0 or negative.\n");
            return;
        }

        if (withdrawalAmount > account.getBalance()) {
            duplicateOutputText(firstName + " " + lastName + dobString + "(" + accountType2 +") has insufficient funds.\n");
            return;
        }

        accountDB.withdraw(account, withdrawalAmount);

        duplicateOutputText(firstName + " " + lastName + dobString + "(" + accountType2 + ") withdraw - balance updated.\n");
    }

    /**
     * This method is executed when the "Deposit" button is clicked. It initiates the deposit process.
     */
    @FXML
    private void handleDepositButtonClick() {

        deposit_withdrawAccountWarning();
        String firstName = firstNameTextField2.getText().trim();
        String lastName = lastNameTextField2.getText().trim();
        String depositAmountString = amountTextField.getText().trim();
        LocalDate dob = dobDatePicker2.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() || depositAmountString.isEmpty()) {
            duplicateOutputText("Missing data for depositing into an account.\n");
            return;
        }

        if (dob == null) {
            duplicateOutputText("Invalid date of birth.\n");
            return;
        }

        if (accountTypeGroup2.getSelectedToggle() == null) {
            showAlert("Warning", "Please select an account type.");
            duplicateOutputText("Missing data for depositing from an account.\n");
            return;
        }

        Date dobDate = new Date(dob.getYear(), dob.getMonthValue(), dob.getDayOfMonth());
        double depositAmount;

        try {
            depositAmount = parseAmount(depositAmountString);
        } catch (IllegalArgumentException e) {
            duplicateOutputText("Not a valid amount.\n");
            return;
        }

        handleDepositCommand(firstName, lastName, dobDate, depositAmount);
        handleClearButtonClick();
    }

    /**
     * Parses the deposit amount from a string and throws an IllegalArgumentException if the format is invalid.
     *
     * @param amountToken The string to be parsed into a numerical value.
     * @return The double value representing the deposit amount.
     * @throws IllegalArgumentException if the string does not match a valid numerical format.
     */
    private double parseAmount(String amountToken) throws IllegalArgumentException {
        if (!amountToken.matches("-?\\d+(\\.\\d+)?")) {
            throw new IllegalArgumentException();
        }
        return Double.parseDouble(amountToken);
    }


    /**
     * Handles the deposit command by finding the account associated with the provided profile and depositing the amount.
     *
     * @param firstName The first name of the account holder.
     * @param lastName The last name of the account holder.
     * @param dob The date of birth of the account holder.
     * @param depositAmount The amount to be deposited into the account.
     */
    private void handleDepositCommand(String firstName, String lastName, Date dob, double depositAmount) {
        String fName = firstName.toUpperCase();
        String lName = lastName.toUpperCase();
        String dobString = dob.getMonth() + "/" + dob.getDay() + "/" + dob.getYear();

        Profile profile = new Profile(fName, lName, dob);
        Account account = accountDB.find2(profile, accountType2);

        if (account == null) {
            duplicateOutputText(firstName + " " + lastName + dobString + "(" + accountType2 + ") is not in the database.\n");
            return;
        }

        executeDeposit(account, depositAmount, firstName, lastName, accountType2, dobString);
    }

    /**
     * Executes the deposit transaction, updating the account's balance if the transaction is successful.
     *
     * @param account The account to which funds will be deposited.
     * @param depositAmount The amount of funds to deposit.
     * @param firstName The first name of the account holder.
     * @param lastName The last name of the account holder.
     * @param accountType The type of the account.
     */
    private void executeDeposit(Account account, double depositAmount, String firstName, String lastName, String accountType, String dobString) {
        if (depositAmount <= 0) {
            duplicateOutputText("Deposit - amount cannot be 0 or negative.\n");
            return;
        }


        accountDB.deposit(account, depositAmount);
        duplicateOutputText(firstName + " " + lastName + dobString + "(" + accountType + ") deposit - balance updated.\n");
    }

    /**
     * When the "Print" button is clicked, this method is called to print the sorted list of accounts.
     */
    @FXML
    private void handlePrintButtonClick() {
        duplicateOutputText(accountDB.printSorted());
    }

    /**
     * When the "Print Interest" button is clicked, this method is invoked to print the fees and interests for accounts.
     */
    @FXML
    private void handlePrintInterestButtonClick() {
        duplicateOutputText(accountDB.printFeesAndInterests());
    }

    /**
     * When the "Print Update" button is clicked, this method is executed to show updated balances for all accounts.
     */
    @FXML
    private void handlePrintUpdateButtonClick() {
        duplicateOutputText(accountDB.printUpdatedBalances());
    }

    /**
     * This method is called when a user wants to import account information from a file.
     * It opens a FileChooser dialog for the user to select a file.
     */
    @FXML
    private void importFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(fileMenuButton.getScene().getWindow());

        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] tokens = line.split(",");

                if (tokens.length < 5) {
                    duplicateOutputText("Incomplete account information.\n");
                    continue;
                }

                String accountType = tokens[0];
                String firstName = tokens[1];
                String lastName = tokens[2];
                String dobString = tokens[3];

                double amount = Double.parseDouble(tokens[4]);
                Date dateOfBirth = parseDate(dobString);
                Profile profile = new Profile(firstName.toUpperCase(), lastName.toUpperCase(), dateOfBirth);










                if (dateOfBirth == null || !isValidDate(dateOfBirth)) {
                    continue;
                }

                if ("CC".equals(accountType.toUpperCase())) {
                    if (!checkUnder24ForCC(dateOfBirth)) {
                        continue;
                    }
                    if (Integer.parseInt(tokens[5]) != 0 && Integer.parseInt(tokens[5]) != 1 && Integer.parseInt(tokens[5]) != 2) {
                        duplicateOutputText("Invalid Campus code!\n");
                        continue;
                    }
                }

                if (!tokens[4].matches("-?\\d+(\\.\\d+)?")) {
                    duplicateOutputText("Not a valid amount.\n");
                    continue;
                }

                if (amount <= 0) {
                    duplicateOutputText("Initial deposit cannot be 0 or negative.\n");
                    continue;
                }

                Account account = null;
                switch (accountType.toUpperCase()) {
                    case "C":
                        Account account2 = accountDB.find2(profile,"CC");
                        if(account2!=null){
                            duplicateOutputText(firstName + " " + lastName + " " + dobString + " (" + accountType + ") is already in the database.\n");
                            break;
                        }
                        account = new Checking(profile, amount);
                        break;
                    case "CC":
                        Account account3 = accountDB.find2(profile,"C");
                        if(account3!=null){
                            duplicateOutputText(firstName + " " + lastName + " " + dobString + " (" + accountType + ") is already in the database.\n");
                            break;
                        }
                        int campusCode = Integer.parseInt(tokens[5]);
                        account = new CollegeChecking(profile, amount, campusCode);
                        break;
                    case "S":
                        int Loyal = Integer.parseInt(tokens[5]);
                        boolean isLoyal = false;
                        if(Loyal==0){
                            isLoyal = false;
                        }
                        else if(Loyal==1){
                            isLoyal = true;

                        }
                        else{
                            duplicateOutputText("Enter isLoyal or not!\n");
                            break;
                        }
                        account = new Savings(profile, amount, isLoyal);
                        break;
                    case "MM":
                        boolean isLoyal1 = true;
                        if(amount<2000){
                            duplicateOutputText("Minumum deposit amount is $2000\n");
                            break;
                        }
                        account = new MoneyMarket(profile, amount, isLoyal1);
                        break;
                    default:
                        duplicateOutputText("Unknown account type: " + accountType + "\n");
                        continue;
                }

                if (account != null) {
                    boolean success = accountDB.open(account);
                    if (success) {
                        duplicateOutputText(firstName + " " + lastName + " " + dobString + " (" + accountType + ") opened.\n");
                    } else {
                        duplicateOutputText( firstName + " " + lastName + " " + dobString + " (" + accountType + ") is already in the database\n");
                    }
                }
            }
            duplicateOutputText("All accounts imported into database from file '" + file.getName() + "'\n");
        }catch(FileNotFoundException e){
            duplicateOutputText("File not found.\n");
        } catch(NumberFormatException e){
            duplicateOutputText("Number format error in file.\n");
        }
    }

    /**
     * This method is used to duplicate output messages across all output text areas of the UI,
     *
     * @param text The message text to be displayed in the output areas.
     */
    private void duplicateOutputText(String text){
        outputArea.appendText(text);
        outputArea1.appendText(text);
        outputArea2.appendText(text);
        outputArea3.appendText(text);

    }

    /**
     * This method creates and displays an alert dialog with a warning icon.
     *
     * @param title The title of the alert dialog, which appears in the title bar.
     * @param content The main content text of the alert, describing the warning or information.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}

