package com.example.travelcompanion.converters;

import static com.example.travelcompanion.model.UnitType.GALLON;
import static com.example.travelcompanion.model.UnitType.KILOMETER;
import static com.example.travelcompanion.model.UnitType.KM_PER_LITER;
import static com.example.travelcompanion.model.UnitType.LITER;
import static com.example.travelcompanion.model.UnitType.MPG;
import static com.example.travelcompanion.model.UnitType.NAUTICAL_MILE;

import com.example.travelcompanion.model.UnitType;

public class FuelConverter {

    public static double convert(UnitType from, UnitType to, double value) {

        if (from.equals(MPG) && to.equals(KM_PER_LITER))
            return value * 0.425;

        if (from.equals(KM_PER_LITER) && to.equals(MPG))
            return value / 0.425;

        if (from.equals(GALLON) && to.equals(LITER))
            return value * 3.785;

        if (from.equals(LITER) && to.equals(GALLON))
            return value / 3.785;

        if (from.equals(NAUTICAL_MILE) && to.equals(KILOMETER))
            return value * 1.852;

        if (from.equals(KILOMETER) && to.equals(NAUTICAL_MILE))
            return value / 1.852;

        value = 0;

        return value;
    }
}
