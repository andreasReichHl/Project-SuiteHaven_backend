package org.example.suiteHaven.exception;

public class BookingConflictException extends RuntimeException{
    public BookingConflictException() {
        super("Booking conflict: the selected dates overlap with an existing booking.");
    }

    public BookingConflictException(String message) {
        super(message);
    }
}
