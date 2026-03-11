package com.example.travelcompanion.model;

public class ConversionResult {

    private double value;
    private String unit;

    public ConversionResult(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}