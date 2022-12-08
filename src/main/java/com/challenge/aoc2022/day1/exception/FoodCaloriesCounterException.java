package com.challenge.aoc2022.day1.exception;

public class FoodCaloriesCounterException extends Exception {

    public FoodCaloriesCounterException(String message) {
        super(message);
    }
    public FoodCaloriesCounterException(String message, Throwable cause) {
        super(message, cause);
    }
}
