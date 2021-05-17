package com.tourguide.exceptions;

public class DayIsOverException extends Exception {
    public DayIsOverException() {
        super("Day is already full");
    }
}
