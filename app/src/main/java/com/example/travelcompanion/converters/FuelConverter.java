package com.example.travelcompanion.converters;

public class FuelConverter {

    public static double convert(String from, String to, double value) {

        if (from.equals("MPG") && to.equals("KM/L"))
            return value * 0.425;

        if (from.equals("KM/L") && to.equals("MPG"))
            return value / 0.425;

        if (from.equals("Gallon") && to.equals("Liter"))
            return value * 3.785;

        if (from.equals("Liter") && to.equals("Gallon"))
            return value / 3.785;

        if (from.equals("Nautical Mile") && to.equals("Kilometer"))
            return value * 1.852;

        if (from.equals("Kilometer") && to.equals("Nautical Mile"))
            return value / 1.852;

        value = 0;

        return value;
    }
}
