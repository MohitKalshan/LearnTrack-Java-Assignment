package com.airtribe.learntrack.exception;

/**
 * Custom exception thrown when user input is invalid.
 * Examples: empty required fields, negative numbers, invalid menu choices.
 */
public class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super(message);
    }
}
