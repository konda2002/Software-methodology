package com.example.project_3_software_methodology;

/**
 * Represents a date with year, month, and day components.
 * Provides methods to validate the date and compare dates.
 *
 * @author Rohan Parikh & Ganesh Konda
 */
public class Date implements Comparable<Date> {
    private final int year; // Event Year
    private final int month; // Event month
    private final int day; // Event day

    /**
     * Constructs a Date object with the specified year, month, and day.
     *
     * @param year  The year component of the date.
     * @param month The month component of the date.
     * @param day   The day component of the date.
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Checks if the date is a valid calendar date.
     *
     * @return true if the date is valid, false otherwise.
     */
    public boolean isValid() {
        if (year < 1 || month < 1 || month > 12 || day < 1) {
            return false;
        }
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
            daysInMonth[2] = 29;
        }
        return day <= daysInMonth[month];
    }

    /**
     * Compares this date with another date.
     *
     * @param other The date to be compared.
     * @return a negative integer, zero, or a positive integer as this date is less than, equal to, or greater than the specified date.
     */
    @Override
    public int compareTo(Date other) {
        if (year != other.year) {
            return Integer.compare(year, other.year);
        }
        if (month != other.month) {
            return Integer.compare(month, other.month);
        }
        return Integer.compare(day, other.day);
    }

    /**
     * Gets the year component of the date.
     *
     * @return The year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the month component of the date.
     *
     * @return The month.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Gets the day component of the date.
     *
     * @return The day.
     */
    public int getDay() {
        return day;
    }

    /**
     * Checks if this date is equal to another object.
     *
     * @param obj The object to be compared.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Date otherDate = (Date) obj;
        return year == otherDate.year && month == otherDate.month && day == otherDate.day;
    }

    /** Method to Test the isValid method with various test cases */
    public static void main(String[] args) {

        testMonthOutOfRange();
        testDayOutOfRange();
        testLeapYear();
    }

    /** Test Case #1 */
    private static void testMonthOutOfRange(){
        Date date = new Date(1999,13,12);
        boolean actualOutput = date.isValid();
        boolean expectedOutput = false;
        System.out.println("**Test case #1: the range for a valid month 1-12");
        testResult(date, expectedOutput, actualOutput);
    }

    /** Test Case #2 */
    private static void testDayOutOfRange(){
        Date date = new Date(2001,11,32);
        boolean actualOutput = date.isValid();
        boolean expectedOutput = false;
        System.out.println("**Test case #2: the range for a valid day 1-28/30/31");
        testResult(date, expectedOutput, actualOutput);
    }

    /** Test Case #3 */
    private static void testLeapYear(){
        Date date = new Date(2023,2,29);
        boolean actualOutput = date.isValid();
        boolean expectedOutput = false;
        System.out.println("**Test case #3: Leap Year comes every 4 years and 2024 is the next one.");
        testResult(date, expectedOutput, actualOutput);
    }

    /** Check if a given test case PASS OR FAIL */
    private static void testResult(Date date, boolean expectedOutput, boolean actualOutput){
        System.out.println("Test input:" + date.getMonth() + "/" + date.getDay() + "/" + date.getYear());
        System.out.println("Expected output: " + expectedOutput);
        System.out.println("Actual output: " + actualOutput);
        if(expectedOutput != actualOutput){
            System.out.println(" (FAIL) \n");
        }
        else{
            System.out.println(" (PASS) \n");

        }
    }
}


