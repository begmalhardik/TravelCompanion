package com.example.travelcompanion.converters;

public class TemperatureConverter {

    public static double convert(String from, String to, double value) {

        if(from.equals("Celsius") && to.equals("Fahrenheit"))
            return (value * 1.8) + 32;

        if(from.equals("Fahrenheit") && to.equals("Celsius"))
            return (value - 32) / 1.8;

        if(from.equals("Celsius") && to.equals("Kelvin"))
            return value + 273.15;

        if(from.equals("Kelvin") && to.equals("Celsius"))
            return value - 273.15;

        return value;
    }
}
