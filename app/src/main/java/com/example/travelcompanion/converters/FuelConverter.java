package com.example.travelcompanion.converters;

public class FuelConverter {

    public static double mpgToKmPerLiter(double mpg) {
        return mpg * 0.425;
    }

    public static double gallonToLiter(double gallon) {
        return gallon * 3.785;
    }

    public static double nauticalMileToKm(double nm) {
        return nm * 1.852;
    }

}
