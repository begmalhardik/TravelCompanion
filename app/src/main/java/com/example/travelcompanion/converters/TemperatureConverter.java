package com.example.travelcompanion.converters;

import static com.example.travelcompanion.model.UnitType.CELSIUS;
import static com.example.travelcompanion.model.UnitType.FAHRENHEIT;
import static com.example.travelcompanion.model.UnitType.KELVIN;

import com.example.travelcompanion.model.UnitType;

public class TemperatureConverter {

    public static double convert(UnitType from, UnitType to, double value) {

        if(from.equals(CELSIUS) && to.equals(FAHRENHEIT))
            return (value * 1.8) + 32;

        if(from.equals(FAHRENHEIT) && to.equals(CELSIUS))
            return (value - 32) / 1.8;

        if(from.equals(CELSIUS) && to.equals(KELVIN))
            return value + 273.15;

        if(from.equals(KELVIN) && to.equals(CELSIUS))
            return value - 273.15;

        value = 0;

        return value;
    }
}
