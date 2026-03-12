package com.example.travelcompanion.utils;

public class ValidationUtils {

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isNumeric(String value) {

        if (isEmpty(value)) return false;

        try {
            Double.parseDouble(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNegative(double value) {
        return value < 0;
    }
}
