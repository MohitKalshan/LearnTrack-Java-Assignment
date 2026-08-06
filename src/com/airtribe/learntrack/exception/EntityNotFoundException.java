package com.airtribe.learntrack.exception;

/**
 * Custom exception thrown when a requested entity (Student, Course, Enrollment)
 * cannot be found in the system.
 */
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
