package com.example.project_3_software_methodology;

/**
 * Enum representing different campus locations.
 * Each campus has an associated numeric code for identification.
 *
 * @author Rohan Parikh & Ganesh Konda
 */
public enum Campus {
    NEW_BRUNSWICK(0), // Campus in New Brunswick
    NEWARK(1),        // Campus in Newark
    CAMDEN(2);        // Campus in Camden

    private final int code;  // Numeric code associated with the campus

    /**
     * Constructor for Campus enum. Initializes the numeric code for each campus.
     *
     * @param code Numeric code representing the campus.
     */
    Campus(int code) {
        this.code = code;
    }

    /**
     * Gets the numeric code associated with the campus.
     *
     * @return The numeric code of the campus.
     */
    public int getCode() {
        return code;
    }

    /**
     * Retrieves the Campus enum constant based on the given numeric code.
     *
     * @param code Numeric code representing a campus.
     * @return The Campus enum constant corresponding to the given code, or null if the code is invalid.
     */
    public static Campus getByCode(int code) {
        for (Campus campus : values()) {
            if (campus.code == code) {
                return campus;
            }
        }
        return null;
    }
}


