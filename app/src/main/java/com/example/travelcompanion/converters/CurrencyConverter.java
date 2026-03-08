package com.example.travelcompanion.converters;

// similarly we will be making fuel and temperature converters

public class CurrencyConverter {

    public static double convert(String from, String to, double amount) {

        double usdAmount = 0;

        switch (from) {
            case "USD": usdAmount = amount; break;
            case "AUD": usdAmount = amount / 1.55; break;
            case "EUR": usdAmount = amount / 0.92; break;
            case "JPY": usdAmount = amount / 148.50; break;
            case "GBP": usdAmount = amount / 0.78; break;
        }

        switch (to) {
            case "USD": return usdAmount;
            case "AUD": return usdAmount * 1.55;
            case "EUR": return usdAmount * 0.92;
            case "JPY": return usdAmount * 148.50;
            case "GBP": return usdAmount * 0.78;
        }

        return 0;
    }
}
