package com.airtribe.learntrack.util;

/**
 * Utility class with static helper methods for validating user input.
 * Keeps validation logic centralized and reusable across the application.
 */
public class InputValidator {

    // Private constructor to prevent instantiation (utility class)
    private InputValidator() {
    }

    /**
     * Checks if a string is non-null and not empty (after trimming whitespace).
     *
     * @param s the string to validate
     * @return true if the string has content, false otherwise
     */
    public static boolean isNonEmptyString(String s) {
        return s != null && !s.trim().isEmpty();
    }

    /**
     * Checks if an integer is positive (greater than zero).
     *
     * @param n the number to validate
     * @return true if positive, false otherwise
     */
    public static boolean isPositiveInt(int n) {
        return n > 0;
    }

    /**
     * Performs a basic email validation check.
     * Checks that the string contains '@' and has content before and after it.
     *
     * @param email the email string to validate
     * @return true if the email passes basic validation, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        int atIndex = email.indexOf('@');
        // Must have '@' with characters before and after it
        return atIndex > 0 && atIndex < email.length() - 1;
    }
}
