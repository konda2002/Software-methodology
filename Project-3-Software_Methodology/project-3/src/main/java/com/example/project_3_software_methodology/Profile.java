package com.example.project_3_software_methodology;

/**
 * Profile class represents a person's profile with first name, last name, and date of birth.
 * It implements Comparable interface to allow comparison between profiles based on last name, first name, and date of birth.
 *
 * @author Rohan Parikh & Ganesh Konda
 */
public class Profile implements Comparable<Profile> {
    private String fname;  // First name of the person
    private String lname;  // Last name of the person
    private Date dob;      // Date of birth of the person

    /**
     * Constructor for creating a Profile object.
     *
     * @param fname First name of the person.
     * @param lname Last name of the person.
     * @param dob   Date of birth of the person.
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Gets the first name of the person.
     *
     * @return The first name of the person.
     */
    public String getFirstName() {
        return fname;
    }

    /**
     * Gets the last name of the person.
     *
     * @return The last name of the person.
     */
    public String getLastName() {
        return lname;
    }

    /**
     * Gets the date of birth of the person.
     *
     * @return The date of birth of the person.
     */
    public Date getDateOfBirth() {
        return dob;
    }

    /**
     * Compares this profile with another profile based on last name, first name, and date of birth.
     *
     * @param other The profile to compare with.
     * @return a negative integer, zero, or a positive integer as this profile is less than, equal to, or greater than the specified profile.
     */
    @Override
    public int compareTo(Profile other) {
        int lastNameComparison = this.lname.compareTo(other.lname);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }

        // If last names are the same, compare first names
        int firstNameComparison = this.fname.compareTo(other.fname);
        if (firstNameComparison != 0) {
            return firstNameComparison;
        }
        return this.dob.compareTo(other.dob);
    }

    /**
     * Checks if this profile is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Profile profile = (Profile) o;
        if (fname != null ? !fname.equals(profile.fname) : profile.fname != null) {
            return false;
        }
        if (lname != null ? !lname.equals(profile.lname) : profile.lname != null) {
            return false;
        }
        return dob != null ? dob.equals(profile.dob) : profile.dob == null;
    }
}

