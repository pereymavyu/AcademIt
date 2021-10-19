package ru.academits.pereyma.temperature.model;

public class Kelvin implements TemperatureScale {
    @Override
    public String getScaleName() {
        return "Kelvin";
    }

    @Override
    public double convertToCelsius(double oldValue) {
        return oldValue - 273.15;
    }

    @Override
    public double convertFromCelsius(double oldValue) {
        return oldValue + 273.15;
    }
}
